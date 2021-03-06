package mu.node.rexweather.app.ViewModels;

import mu.node.rexweather.app.Models.CurrentWeather;
import mu.node.rexweather.app.Models.WeatherForecast;
import mu.node.rexweather.app.Services.WeatherService;
import rx.Observable;

import java.util.List;

/**
 * Created by Zack on 1/15/15.
 */
public class WeatherViewModel {
    private Observable<CurrentWeather> currentWeather;
    private Observable<List<WeatherForecast>> weatherForecasts;
    private WeatherService weatherService;

    public WeatherViewModel(){
        weatherService = new WeatherService();
    }

    public Observable<CurrentWeather> whenGetCurrentWeather() {
        return currentWeather;
    }

    public Observable<List<WeatherForecast>> whenGetWeatherForecasts() {
        return weatherForecasts;
    }

    public WeatherViewModel setRefreshSignal(Observable<RefreshEvent> refreshEventObservable) {
        currentWeather = refreshEventObservable
                .flatMap(location -> weatherService.fetchCurrentWeather(116.0, 32.0));

        weatherForecasts =  refreshEventObservable
                .flatMap(location -> weatherService.fetchWeatherForecasts(116.0,36.0));

        return this;
    }



    public void setWeatherForecasts(Observable<List<WeatherForecast>> weatherForecasts) {
        this.weatherForecasts = weatherForecasts;
    }

    public static class RefreshEvent {

    }
}
