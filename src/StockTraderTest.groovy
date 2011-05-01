@Grab('junit:junit:4.8.2')

import org.junit.*

class StockTraderTest extends GroovyTestCase
{
	StockTrader stockTrader;
	
	@Before
	void setup() 
	{
		stockTrader = new StockTrader();
	}
	
	@Test
	void buystock_valid()
	{
		assertTrue(stockTrader.buy("AAPL"))
	}
	
}