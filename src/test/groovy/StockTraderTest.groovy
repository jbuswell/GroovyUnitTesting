import org.junit.*

class StockTraderTest extends GroovyTestCase
{
	StockTrader stockTrader = new StockTrader();
	
	@Test
	void test_buystock_valid()
	{
		def trade = [ 'isSuccess' : { true } ] as Trade
		def service = [ 'purchaseAtCurrentPrice' : { trade } ] as StockTraderService
		stockTrader.setStockTraderService(service)
		assertTrue(stockTrader.buy("AAPL"))
	}
	
}