package ua.in.zloch.converters;

import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.dto.RegionListDTO;
import ua.in.zloch.entity.Category;
import ua.in.zloch.entity.Crime;
import ua.in.zloch.entity.Region;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RegionToRegionDtoConverterTest {

    private RegionToRegionDtoConverter regionToRegionDtoConverter = new RegionToRegionDtoConverter();

    @Test
    public void testConvert() throws Exception {
        RegionListDTO regionListDTO = regionToRegionDtoConverter.convert(createTestRegionList());

        assertNotNull(regionListDTO);
        assertNotNull(regionListDTO.getRegions());
        assertEquals(1, regionListDTO.getRegions().size());

        RegionListDTO.RegionDTO regionDTO = regionListDTO.getRegions().get(0);
        assertNotNull(regionDTO.getRegion());
        assertEquals("Frankivsky", regionDTO.getRegion().get(RegionListDTO.RegionDTO.NAME));

        assertNotNull(regionDTO.getRegion());
        Map<String, Object> expectedProperties = new HashMap<String, Object>();
        Region testRegion = createTestRegion();
        expectedProperties.put(RegionListDTO.RegionDTO.ID, testRegion.getId());
        expectedProperties.put(RegionListDTO.RegionDTO.NAME, testRegion.getName());
        expectedProperties.put(RegionListDTO.RegionDTO.KOATU, testRegion.getKoatuu());
        expectedProperties.put(RegionListDTO.RegionDTO.BOUNDARIES, testRegion.getBoundaries());

        assertEqualsMapEpsilon(expectedProperties, regionDTO.getRegion());
    }

    @Test
    public void testConvertEmptyList(){
        RegionListDTO regionListDTO = regionToRegionDtoConverter.convert(new ArrayList<Region>());
        assertNotNull(regionListDTO);
        assertNotNull(regionListDTO.getRegions());
        assertEquals(0, regionListDTO.getRegions().size());
    }

    private List<Region> createTestRegionList(Region region){
        List<Region> list = new ArrayList<Region>();
        list.add(region);
        return list;
    }

    private List<Region> createTestRegionList(){
        return this.createTestRegionList(createTestRegion());
    }

    private Region createTestRegion() {
        Region region = new Region();
        region.setId(123l);
        region.setName("Frankivsky");
        region.setKoatuu("12312");
        region.setBoundaries("24.01405,49.783669 24.018125,49.801882 24.018901,49.805269 24.01901,49.805762 24.019235,49.808163 24.019446,49.810959 24.019474,49.811333 24.019499,49.811723 24.019505,49.811811 24.019652,49.814277 24.019753,49.814674 24.020118,49.818499 24.020226,49.819868 24.020738,49.824769 24.02094,49.825192 24.020398,49.825341 24.019608,49.825731 24.017947,49.826593 24.018881,49.827531 24.019088,49.827768 24.019141,49.82788 24.019326,49.827884 24.019638,49.827881 24.019985,49.828242 24.020131,49.829871 24.019887,49.830461 24.020687,49.831096 24.020756,49.831177 24.018426,49.832181 24.018383,49.832281 24.018378,49.83235 24.018388,49.832555 24.018522,49.832686 24.018802,49.833194 24.019124,49.83368 24.019278,49.83382 24.019543,49.834026 24.020053,49.834387 24.020227,49.834573 24.019608,49.834603 24.018924,49.834645 24.017416,49.834648 24.017091,49.834653 24.016383,49.834663 24.014985,49.834669 24.014401,49.834671 24.013966,49.834672 24.012829,49.834681 24.011166,49.834705 24.009771,49.834749 24.007755,49.834801 24.007484,49.834823 24.007155,49.834849 24.005382,49.835425 24.004594,49.836019 24.003914,49.837077 24.00386,49.837059 24.00359,49.836978 24.002919,49.836776 24.002739,49.836755 24.002538,49.836731 24.001481,49.836537 24.000926,49.836435 23.997634,49.835841 23.994296,49.835256 23.99242,49.834908 23.991079,49.834677 23.990323,49.834549 23.98588,49.833729 23.983873,49.833408 23.98281,49.833241 23.979783,49.832713 23.978109,49.832402 23.977192,49.83224 23.976149,49.832069 23.975979,49.832033 23.974767,49.831834 23.97463,49.83181 23.973746,49.831673 23.973483,49.83163 23.973383,49.831615 23.973023,49.83156 23.972769,49.831521 23.972622,49.831499 23.972621,49.831371 23.972693,49.830152 23.972694,49.830151 23.972693,49.830151 23.972923,49.82936 23.973259,49.829391 23.973778,49.828877 23.974279,49.828345 23.974461,49.828196 23.974497,49.828082 23.974797,49.827944 23.975153,49.82774 23.975576,49.827587 23.975976,49.827453 23.976469,49.827337 23.977235,49.827157 23.980639,49.826129 23.980759,49.826091 23.983633,49.825137 23.984622,49.824845 23.987117,49.82407 23.987352,49.823997 23.987329,49.823944 23.987352,49.823937 23.989106,49.823416 23.989226,49.823394 23.988632,49.821262 23.98834,49.820383 23.987753,49.818865 23.987684,49.818685 23.987592,49.818448 23.987514,49.818297 23.987405,49.81803 23.986909,49.817126 23.985618,49.814881 23.984542,49.81297 23.984355,49.812606 23.984102,49.812424 23.98354,49.812042 23.982987,49.811665 23.982885,49.811477 23.982325,49.809099 23.98141,49.805496 23.980991,49.803519 23.980647,49.802108 23.980441,49.801214 23.980581,49.801108 23.980522,49.801085 23.980456,49.801088 23.980456,49.80108 23.98154,49.800987 23.982116,49.800827 23.982952,49.80015 23.983319,49.800044 23.983343,49.800037 23.98366,49.799945 23.98366,49.799901 23.98446,49.799717 23.985201,49.799283 23.98579,49.798911 23.986519,49.798401 23.988036,49.798265 23.995093,49.797521 23.996386,49.7974 23.99735,49.797629 23.999173,49.797508 24.000866,49.797371 24.001172,49.797424 24.002336,49.797349 24.00263,49.797166 24.008299,49.796551 24.009663,49.796407 24.010521,49.796209 24.011441,49.795963 24.01231,49.79623 24.012477,49.795321 24.012555,49.795144 24.012377,49.794443 24.012958,49.794398 24.012941,49.794206 24.012433,49.792473 24.01243,49.792462 24.012219,49.792227 24.01224,49.790377 24.012255,49.78919 24.012474,49.786602 24.01254,49.786267 24.012526,49.785755 24.012498,49.785539 24.012387,49.785144 24.012013,49.783903 24.01405,49.783669 24.014012,49.783499 24.014052,49.783669 24.01405,49.783669 24.014012,49.783499");

        return region;
    }


    public static void assertEqualsMapEpsilon(Map<String,Object> expected, Map<String,Object> actual) {
        assertEquals(expected.size(), actual.size());
        for(Map.Entry<String,Object> value:expected.entrySet()){
            Object actualValue = actual.get(value.getKey());
            assertNotNull(actualValue);
            assertEquals(value.getValue(), actualValue);
        }
    }
}