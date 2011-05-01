import org.junit.*

class StockTraderTest extends GroovyTestCase
{
	StockTrader stockTrader = new StockTrader();
	
	@Test
	void test_buystock_valid()
	{
		assertTrue(stockTrader.buy("AAPL"))
	}
	
}