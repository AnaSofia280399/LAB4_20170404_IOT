package com.example.lab4_20170404.entity;

import java.util.List;

public class Clima {

    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private String visibility;
    private Wind wind;
    private Clouds clouds;
    private String dt;
    private Sys sys;
    private String timezone;
    private String id;
    private String name;
    private String cod;

    public static class Coord {
        private String lon;
        private String lat;

        // Getters y Setters
        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
    public static class Weather {
        private String id;
        private String main;
        private String description;
        private String icon;

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
    public static class Main {
        private String temp;
        private String feels_like;
        private String temp_min;
        private String temp_max;
        private String pressure;
        private String humidity;
        private String sea_level;
        private String grnd_level;

        // Getters y Setters
        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(String feels_like) {
            this.feels_like = feels_like;
        }

        public String getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(String temp_min) {
            this.temp_min = temp_min;
        }

        public String getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(String temp_max) {
            this.temp_max = temp_max;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getSea_level() {
            return sea_level;
        }

        public void setSea_level(String sea_level) {
            this.sea_level = sea_level;
        }

        public String getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(String grnd_level) {
            this.grnd_level = grnd_level;
        }
    }

    public static class Wind {
        private String speed;
        private String deg;
        private String gust;

        // Getters y Setters
        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDeg() {
            try {
                double windDegree = Double.parseDouble(deg);

                // Array de direcciones cardinales principales
                String[] directions = {"Norte", "Norte", "Norte", "Noreste", "Este", "Sureste", "Sur", "Sur", "Sur", "Suroeste", "Oeste", "Noroeste", "Norte", "Norte", "Norte", "Noreste"};

                // Calcular el índice en el array de direcciones
                int index = (int) Math.round((windDegree % 360) / 22.5);

                // Asignar la dirección correspondiente al viento
                return directions[(index + 16) % 16];
            } catch (NumberFormatException e) {
                // Manejar el error si la cadena no es un número
                return ""; // O retorna un valor por defecto si no se puede convertir
            }
        }



        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getGust() {
            return gust;
        }

        public void setGust(String gust) {
            this.gust = gust;
        }
    }

    public static class Clouds {
        private String all;

        // Getters y Setters
        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }

    public static class Sys {
        private String type;
        private String id;
        private String country;
        private long sunrise;
        private long sunset;

        // Getters y Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
