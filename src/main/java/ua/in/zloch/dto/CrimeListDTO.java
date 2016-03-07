package ua.in.zloch.dto;

import ua.in.zloch.dto.geojson.Geometry;
import ua.in.zloch.dto.geojson.Type;

import java.util.*;

public class CrimeListDTO {
    private Type type = Type.FeatureCollection;
    private List<CrimeDTO> features = new ArrayList<>();

    public Type getType() {
        return type;
    }

    public List<CrimeDTO> getFeatures() {
        return features;
    }

    public void addFeature(CrimeDTO feature) {
        this.features.add(feature);
    }

    public class CrimeDTO {
        public static final String ID = "id";
        public static final String DATE = "date";
        public static final String CATEGORY_ID = "category.id";
        public static final String REGION_NAME = "region.name";
        public static final String REGION_KOATUU = "region.koatuu";

        private Type type;
        private Geometry geometry;
        private Map<String, Object> properties;

        public CrimeDTO() {
            type = Type.Feature;
            geometry = new Geometry();
            geometry.setType(Type.Point);
            geometry.setCoordinates(0.0, 0.0);
            properties = new HashMap<>();
        }

        public void setCoordinates(double latitude, double longitude) {
            this.geometry.setCoordinates(latitude, longitude);
        }

        public void setId(long id) {
            this.properties.put(ID, id);
        }

        public void setDate(Date date) {
            this.properties.put(DATE, date.getTime());
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public Map<String, Object> getProperties() {
            return properties;
        }

        public Type getType() {
            return type;
        }

        public void setCategoryId(long categoryId) {
            this.properties.put(CATEGORY_ID, categoryId);
        }

        public void setRegionName(String regionName) {
            this.properties.put(REGION_NAME, regionName);
        }

        public void setRegionKoatuu(String regionKoatuu) {
            this.properties.put(REGION_KOATUU, regionKoatuu);
        }
    }

}