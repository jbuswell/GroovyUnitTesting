import org.junit.*

import groovy.util.GroovyTestCase

import static Trade.*

class StockTraderTest extends GroovyTestCase
{
	StockTrader stockTrader;
	
	@Before
	void setUp()
	{
		stockTrader = new StockTrader();
	}
	
	@Test
	void test_buystock_valid()
	{
		def trade = [ 'isSuccess' : { true } ] as Trade
		def service = [ 'purchaseAtCurrentPrice' : { trade } ] as StockTraderService
		stockTrader.setStockTraderService(service)
		assertTrue(stockTrader.buy("AAPL"))
	}
	
	@Test 
	void test_get_most_active() 
	{
		def stocks = ['AAPL', 'GOOG', 'MSFT', 'INTC', 'IBM', 'ORCL', 'ADBE', 'RIMM', 'HPQ', 'CSCO']
		def service = [ 'getMostActive' : { return stocks } ] as StockTraderService
		stockTrader.stockTraderService = service
		assertArrayEquals(stocks.toArray(), stockTrader.tenMostActive().toArray())
	}

	@Test 
	void test_get_most_active_case() 
	{
		def stocks = ['AAPL', 'GOOG', 'MSFT', 'INTC', 'IBM', 'ORCL', 'ADBE', 'RIMM', 'HPQ', 'csco']
		def service = [ 'getMostActive' : { return stocks } ] as StockTraderService
		stockTrader.stockTraderService = service
		def result = stockTrader.tenMostActive()
		assert stocks.collect{ it.toUpperCase() } == result
	}
	
	@Test
	void test_sell_at_market()
	{
		def trade = new TradeImpl(Type.sell, 100d, 100.50d, true)
		//track stocks requested -- impossible to do in Java
		//without creating your own mocks
		def stocksList = []
		//necessary to include parameters when more than one
		def service = [ 'sell' : { s, q -> return trade }, 
						'getPrice' : { return 100.50d },
						'exists' : { s -> stocksList.add(s); if( 'MSFT' == s ) { return true } else { return false } } 
					  ] as StockTraderService
		stockTrader.stockTraderService = service
		assert (100.50 * 100) == stockTrader.sellAtMarket("MSFT", 100);
		assertNull stockTrader.sellAtMarket("BLAH", 100);
		assert ['MSFT', 'BLAH'] == stocksList
	}
	
}
