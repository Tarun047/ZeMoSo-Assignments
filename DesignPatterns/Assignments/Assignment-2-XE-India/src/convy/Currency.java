package convy;
import java.util.HashMap;
import java.util.Map;

class Currency
{
    private static Map<String,Double> moneyMap;
    private static Currency _internal;
    private Currency()
    {
        moneyMap = new HashMap<>();
    }

    static Currency getInstance() {

        if(_internal==null)
            _internal = new Currency();
        return _internal;
    }

    void addOrUpdateCurrency(String country,Double valueInRupees)
    {
        moneyMap.put(country.toLowerCase(),valueInRupees);
    }

    double getCurrency(String country)
    {
        return moneyMap.get(country.toLowerCase());
    }
}
