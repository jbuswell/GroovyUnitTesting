import org.junit.*

import groovy.util.GroovyTestCase

import static Trade.*

class StockTraderTest extends GroovyTestCase
{
	StockTrader stockTrader = new StockTrader();
	
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
		def stocks = ['APPL', 'GOOG', 'MSFT', 'INTC', 'IBM', 'ORCL', 'ADBE', 'RIMM', 'HPQ', 'CSCO']
		def service = [ 'getMostActive' : { return stocks } ] as StockTraderService
		stockTrader.stockTraderService = service
		assertArrayEquals(stocks.toArray(), stockTrader.tenMostActive().toArray())
	}

	@Test 
	void test_get_most_active_case() 
	{
		def stocks = ['APPL', 'GOOG', 'MSFT', 'INTC', 'IBM', 'ORCL', 'ADBE', 'RIMM', 'HPQ', 'csco']
		def service = [ 'getMostActive' : { return stocks } ] as StockTraderService
		stockTrader.stockTraderService = service
		def result = stockTrader.tenMostActive()
		assert stocks.collect{ it.toUpperCase() } == result
	}
	
	@Test
	void test_sell_at_market()
	{
		println "hello 1"
		def trade = new TradeImpl(Type.sell, 100d, 100.50d, true)
		//start with empty map
		def service = [ 'sell' : { s, q -> return trade }, 
						'getPrice' : { return 100.50d },
						'exists' : { s -> if( 'MSFT' == s ) { return true } else { return false } } 
					  ] as StockTraderService
		//necessary to include parameters when more than one
		stockTrader.stockTraderService = service
		assert (100.50 * 100) == stockTrader.sellAtMarket("MSFT", 100);
		assertNull stockTrader.sellAtMarket("BLAH", 100);
	}
	
}
