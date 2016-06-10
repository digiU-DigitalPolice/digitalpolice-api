package ua.in.zloch.batch.geohash;

import ch.hsr.geohash.GeoHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import ua.in.zloch.core.entity.Crime;

public class GeohashProcessor implements ItemProcessor<Crime, Crime> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Crime process(Crime crime) throws Exception {
        String hash = GeoHash.withBitPrecision(crime.getLatitude(), crime.getLongitude(), 60).toBase32();
        crime.setGeohash(hash);
        log.info("Geohash for crime: " + crime.getId() + " is: " + hash);
        return crime;
    }

}
