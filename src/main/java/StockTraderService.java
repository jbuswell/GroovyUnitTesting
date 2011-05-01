public interface StockTraderService 
{
	Boolean exists(String symbol);
	Trade purchaseAtCurrentPrice(String symbol);
}