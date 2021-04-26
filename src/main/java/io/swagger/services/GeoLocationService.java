package io.swagger.services;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import io.swagger.model.GeoIP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeoLocationService {

    private DatabaseReader dbReader;

    public GeoLocationService(@Value("classpath:/GeoLite2-City_20210302/GeoLite2-City.mmdb") Resource geoLiteCity) throws IOException {
        //File database = new File("src/main/resources/GeoLite2-City_20210302/GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(geoLiteCity.getInputStream()).build();
    }

    public GeoIP getLocation(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String countryName = response.getCountry().getName();
        String cityName = response.getCity().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();
        return new GeoIP(ip, countryName, cityName, latitude, longitude);
    }

    public List<GeoIP> getLocations(List<String> ipList) throws IOException, GeoIp2Exception {
        List<GeoIP> locations = new ArrayList<>();
        for(String ip : ipList) {
            locations.add(getLocation(ip));
        }
        return locations;
    }

}
