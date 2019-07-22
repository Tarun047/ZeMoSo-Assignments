package convy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class WorldBank implements Publisher {
    private static Set<Subscriber> listeners;
    private static Currency currency;


    public WorldBank()
    {
        currency  = Currency.getInstance();
        listeners = new HashSet<>();
    }

    public double getPrice(String country,double units)
    {
        return currency.getCurrency(country)*units;
    }

    public void updateExchanges(String country,double newValue)
    {
        currency.addOrUpdateCurrency(country,newValue);
        notifyListeners();
    }

    public void autoUpdateExchanges()
    {
        try
        {
           URL url = new URL("https://api.exchangeratesapi.io/latest?base=INR");
            Map<String, Object> map = new Gson().fromJson(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8), new TypeToken<Map<String,Object>>(){}.getType());
            Map<String,Double> cMap = new Gson().fromJson(map.get("rates").toString(),new TypeToken<Map<String,Double>>(){}.getType());
            for(String s:cMap.keySet())
                currency.addOrUpdateCurrency(s,1d/cMap.get(s));
            notifyListeners();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void register(Subscriber mListener) {
        listeners.add(mListener);
    }


    @Override
    public void unregister(Subscriber mListener) {
        listeners.remove(mListener);
    }

    @Override
    public void notifyListeners() {

        for(Subscriber listener: listeners)
        {
            listener.update();
        }
    }

}
