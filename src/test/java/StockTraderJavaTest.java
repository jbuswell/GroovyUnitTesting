import org.junit.*;
import static org.junit.Assert.*;

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
}