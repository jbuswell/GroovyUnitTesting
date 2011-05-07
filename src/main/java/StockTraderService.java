import java.util.List;

public interface StockTraderService 
{
	Boolean exists(String symbol);
	Double getPrice(String symbol);
	Trade purchaseAtCurrentPrice(String symbol);
	Trade sell(String symbol, Double price);
	List<String> getMostActive(Integer num);
}