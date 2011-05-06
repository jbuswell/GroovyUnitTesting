import java.util.List;

public interface StockTraderService 
{
	Boolean exists(String symbol);
	Trade purchaseAtCurrentPrice(String symbol);
	List<String> getMostActive(Integer num);
}