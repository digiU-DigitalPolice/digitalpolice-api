package ua.in.zloch.dto;

import java.util.*;

public class RegionListDTO {
    private List<RegionDTO> regions = new ArrayList<>();

    public List<RegionDTO> getRegions() {
        return regions;
    }

    public void addRegion(RegionDTO district) {
        this.regions.add(district);
    }

    public class RegionDTO {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String KOATU = "KOATU";
        public static final String BOUNDARIES = "boundaries";

        private Map<String, Object> region;

        public RegionDTO() {
            region = new HashMap<>();
        }

        public void setId(long id) {
            this.region.put(ID, id);
        }

        public void setName(String name) {
            this.region.put(NAME, name);
        }

        public void setKoatu(String koatu) {
            this.region.put(KOATU, koatu);
        }

        public void setBoundaries(String boundaries) {
            this.region.put(BOUNDARIES, boundaries);
        }

        public Map<String, Object> getRegion() {
            return region;
        }
    }
}