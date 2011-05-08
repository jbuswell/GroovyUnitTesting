import java.util.*;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StockTraderJavaTest
{
	private StockTrader stockTrader = new StockTrader();
	
	@Test
	public void test_buystock_valid()
	{
		Trade trade = new TradeImpl(Trade.Type.buy, 100.0, 100.0, true);
		StockTraderServiceMock service = new StockTraderServiceMock();
		service.setTrade(trade);
		stockTrader.setStockTraderService(service);
		assertTrue(stockTrader.buy("AAPL"));
	}
	
	
	@Test 
	public void test_get_most_active() 
	{
		List<String> stocks = new ArrayList<String>();
		stocks.add("APPL");
		stocks.add("GOOG");
		stocks.add("MSFT");
		stocks.add("INTC");
		stocks.add("IBM");
		stocks.add("ORCL");
		stocks.add("ADBE");
		stocks.add("RIMM");
		stocks.add("HPQ");
		stocks.add("CSCO");
		StockTraderServiceMock service = new StockTraderServiceMock();
		service.setMostActive(stocks);
		stockTrader.setStockTraderService(service);
		assertArrayEquals(stocks.toArray(), stockTrader.tenMostActive().toArray());
	}

	@Test 
	public void test_get_most_active_case() 
	{
		//Can not abstract due to last element being different
		List<String> stocks = new ArrayList<String>();
		stocks.add("APPL");
		stocks.add("GOOG");
		stocks.add("MSFT");
		stocks.add("INTC");
		stocks.add("IBM");
		stocks.add("ORCL");
		stocks.add("ADBE");
		stocks.add("RIMM");
		stocks.add("HPQ");
		stocks.add("csco");
		StockTraderServiceMock service = new StockTraderServiceMock();
		service.setMostActive(stocks);
		stockTrader.setStockTraderService(service);
		//No easy way to upper case all elements in list
		List<String> upperCaseStocks = new ArrayList<String>();
		for(String  s : stocks)
		{
			upperCaseStocks.add(s.toUpperCase());
		}
		assertArrayEquals(upperCaseStocks.toArray(), stockTrader.tenMostActive().toArray());
	}
	
	//Can't duplicate functionality of tracking which stocks requested
	@Test
	public void test_sell_at_market()
	{
		Trade trade = new TradeImpl(Trade.Type.sell, 100d, 100.50d, true);
		StockTraderService mockService = mock(StockTraderService.class);
		when(mockService.sell(anyString(), anyDouble())).thenReturn(trade);
		when(mockService.getPrice(anyString())).thenReturn(100.50);
		when(mockService.exists(eq("MSFT"))).thenReturn(true);
		when(mockService.exists(eq("BLAH"))).thenReturn(false);
		stockTrader.setStockTraderService(mockService);
		assertEquals((100.50 * 100), stockTrader.sellAtMarket("MSFT", 100d), 0.0);
		assertNull(stockTrader.sellAtMarket("BLAH", 100d));
	}
}