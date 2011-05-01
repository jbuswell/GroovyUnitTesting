public class StockTrader
{
	private StockTraderService stockTraderService;
	
	public boolean buy(String symbol)
	{
		Trade trade = stockTraderService.purchaseAtCurrentPrice(symbol);
		return trade.isSuccess();
	}
	
	public StockTraderService getStockTraderService()
	{
		return stockTraderService;
	}
	
	public void setStockTraderService(StockTraderService stockTraderService)
	{
		this.stockTraderService = stockTraderService;
	}
}
