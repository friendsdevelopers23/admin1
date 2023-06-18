package com.calsoft.pos.controller.units;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.cart.SalesFlatQuote;
import com.calsoft.pos.model.cart.SalesFlatQuoteItem;
import com.calsoft.pos.service.CartService;
import com.calsoft.utils.UserUtils;

@RequestMapping("/api")
@RestController
public class CartController {

	@Autowired
	public CartService cartService;

	@Autowired
	public UserUtils userUtils;

	Logger logger = LoggerFactory.getLogger(CartController.class);

	/***
	 * this method using cart page details show with customerId get login into
	 * credentials based open
	 *
	 * @param customerId
	 * @return
	 * @throws Throwable
	 */
	@GetMapping("/cart/customer")
	public SalesFlatQuote findByCustomerId(@RequestHeader("x-tenant") String tenantId, HttpServletRequest request)
			throws Throwable {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);
		return cartService.fetchByCustomerId(customerId, tenantId);
	}

//	@GetMapping("/cart/customer/count")
//	public Object findCountofProductInCustomerCart(@RequestHeader("x-tenant") String tenantId, HttpServletRequest request)
//			throws Throwable {
//		String token = userUtils.getToken(request);
//		String customerId = userUtils.getUserIdFromToken(token);
//		return cartService.findCountofProductInCustomerCart(customerId, tenantId);
//	}

	/***
	 * this method using CustomerId And EntityId based show cart items
	 * 
	 * @param customerId
	 * @param entityId
	 * @param pageable
	 * @return
	 */
	@GetMapping("/cart/outOfstock/{entityId}")
	public ResponseWrapper findByCustomerIdAndEntityId(@PathVariable(value = "entityId") String entityId,
			Pageable pageable, @RequestHeader("x-tenant") String tenantId, HttpServletRequest request) {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);
		return cartService.findByCustomerIdAndEntityId(customerId, entityId, pageable, tenantId);
	}

	/***
	 * this method using customer Id based cart count show
	 * 
	 * @param customerId
	 * @return
	 * @throws ParseException
	 */
	@GetMapping("/cart/customer/cartCount")
	public Long findQuoteItemCount(@RequestHeader("x-tenant") String tenantId, HttpServletRequest request)
			throws ParseException {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);
		return cartService.findQuoteItemCount(customerId, tenantId);
	}

	/***
	 * this method using Delete cart qty count
	 * 
	 * @param salesFlatQuoteItem
	 * @return
	 */
	@DeleteMapping("/cart/removeOutOfStockItems")
	public ResponseWrapper deleteOutOfStockItemFromCart(@RequestBody List<SalesFlatQuoteItem> salesFlatQuoteItem,
			@RequestHeader("x-tenant") String tenantId) {

		return cartService.deleteOutOfStockItemFromCart(salesFlatQuoteItem, tenantId);
	}

	/***
	 * this method remove all cart details information and customerId based remove
	 *
	 * @param customerId
	 * @return
	 */
	@DeleteMapping("/cart/{entityId}")
	public ResponseWrapper deleteCartItems(@PathVariable String entityId, @RequestHeader("x-tenant") String tenantId,
			HttpServletRequest request) {
		
		return cartService.deleteById(entityId, tenantId);
	}
	
	


	/***
	 * this method using add to cart new product in web menakart used
	 *
	 * @param salesFlatQuote
	 * @return
	 * @throws Throwable
	 */
	@PostMapping("/cart")
	public ResponseWrapper saveCart(@RequestBody final SalesFlatQuote salesFlatQuote,
			@RequestHeader("x-tenant") String tenantId, HttpServletRequest request) throws Throwable {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);

		salesFlatQuote.setBillerId(Long.valueOf(customerId));

		return cartService.saveCartItem(salesFlatQuote, tenantId);
	}

	/***
	 * this method using cart page multiple product adds cards item id based remove
	 * single product used
	 *
	 * @param quoteId
	 * @param itemId
	 * @param salesFlatQuoteItem
	 * @return
	 */

	@DeleteMapping("/cart/{quoteId}/{itemId}")
	public ResponseWrapper deleteItemFromCart(@PathVariable String quoteId, @PathVariable String itemId,
			@RequestBody SalesFlatQuoteItem salesFlatQuoteItem, @RequestHeader("x-tenant") String tenantId) {
		return cartService.deleteCartItem(quoteId, itemId, salesFlatQuoteItem, tenantId);
	}

	@GetMapping("/weigth")
	public Double getWeight(@RequestParam(value = "compValue") String compValue) {
		return cartService.getWeight(compValue);
	}

	/***
	 * this method using add to cart new product in web menakart used
	 *
	 * @param salesFlatQuote
	 * @return
	 * @throws Throwable
	 */
	@PostMapping("/cart/suspended")
	public ResponseWrapper saveSuspendedCart(@RequestBody final SalesFlatQuote salesFlatQuote,
			@RequestHeader("x-tenant") String tenantId, HttpServletRequest request) throws Throwable {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);

		salesFlatQuote.setBillerId(Long.valueOf(customerId));
		salesFlatQuote.setIsSuspended(1);
		salesFlatQuote.setIsVirtual(0);
		
		

		return cartService.saveSuspendedCart(salesFlatQuote, tenantId);
	}

	/***
	 * this method using add to cart new product in web menakart used
	 *
	 * @param salesFlatQuote
	 * @return
	 * @throws Throwable
	 */
	@PostMapping("/cart/suspended/sales")
	public ResponseWrapper saveCartSuspendedSales(@RequestBody final SalesFlatQuote salesFlatQuote,
			@RequestHeader("x-tenant") String tenantId, HttpServletRequest request) throws Throwable {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);

		salesFlatQuote.setBillerId(Long.valueOf(customerId));

		return cartService.saveCartSuspendedSales(salesFlatQuote, tenantId);
	}

	/***
	 * this method using cart page details show with customerId get login into
	 * credentials based open
	 *
	 * @param customerId
	 * @return
	 * @throws Throwable
	 */
	@GetMapping("/cart/suspended/customer")
	public Page<SalesFlatQuote> findSalesSuspended(@RequestHeader("x-tenant") String tenantId,
			HttpServletRequest request, Pageable pageable) throws Throwable {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);
		return cartService.findSalesSuspended(customerId, tenantId, pageable);
	}
	
	/***
	 * this method using CustomerId And EntityId based show cart items
	 * 
	 * @param customerId
	 * @param entityId
	 * @param pageable
	 * @return
	 */
	@GetMapping("/cart/suspended/{entityId}")
	public SalesFlatQuote findBySuspendedSales(@PathVariable(value = "entityId") String entityId,
			@RequestHeader("x-tenant") String tenantId, HttpServletRequest request) {;
		return cartService.findByEntityId(entityId,tenantId);
	}


}