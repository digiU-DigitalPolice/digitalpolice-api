package ua.in.zloch.batch.geohash;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.in.zloch.batch.TestJobConfiguration;
import ua.in.zloch.core.entity.Crime;
import ua.in.zloch.core.repository.CrimeRepository;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
@SpringApplicationConfiguration(classes = {GeohashJobConfiguration.class, TestJobConfiguration.class})
public class GeohashJobConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private CrimeRepository crimeRepository;

    @After
    public void cleanUp() {
        Iterator<Crime> crimeIterator = crimeRepository.findAll().iterator();
        while (crimeIterator.hasNext()) {
            crimeRepository.delete(crimeIterator.next());
        }
    }

    @Test
    public void testGeohashJobSetsGeohashesToCrimes() throws Exception {
        // Given
        Long crimeOneId = createCrime("already_set");
        Long crimeTwoId = createCrime(null);
        Long crimeThreeId = createCrime(null);

        // When
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // Then
        assertThat("job execution status", jobExecution.getExitStatus(), is(ExitStatus.COMPLETED));
        StepExecution firstStepExecution = jobExecution.getStepExecutions().iterator().next();
        assertThat("job read count", firstStepExecution.getReadCount(), is(2));

        Crime one = crimeRepository.findOne(crimeOneId);
        assertEquals("already_set", one.getGeohash());

        Crime two = crimeRepository.findOne(crimeTwoId);
        assertNotNull(two.getGeohash());

        Crime three = crimeRepository.findOne(crimeThreeId);
        assertNotNull(three.getGeohash());
    }

    private Long createCrime(String geohash) {
        Crime crime = new Crime();
        crime.setGeohash(geohash);
        return crimeRepository.save(crime).getId();
    }
}