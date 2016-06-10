package ua.in.zloch.batch.geohash;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import ua.in.zloch.core.entity.Crime;
import ua.in.zloch.core.repository.CrimeRepository;

import java.util.HashMap;

@Configuration
public class GeohashJobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CrimeRepository crimeRepository;

    @Bean
    public Job geohashJob() {
        return jobs.get("geohashJob")
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<Crime, Crime>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .build();
    }

    @Bean
    public ItemReader<Crime> reader() {
        RepositoryItemReader<Crime> reader = new RepositoryItemReader<>();
        reader.setRepository(crimeRepository);
        reader.setMethodName("findByGeohashIsNull");
        HashMap<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("id", Sort.Direction.ASC);
        reader.setSort(sortMap);
        return reader;
    }

    @Bean
    public ItemProcessor<Crime, Crime> processor() {
        return new GeohashProcessor();
    }

    @Bean
    public ItemWriter<Crime> writer() {
        RepositoryItemWriter<Crime> writer = new RepositoryItemWriter<>();
        writer.setRepository(crimeRepository);
        writer.setMethodName("save");
        return writer;
    }

}
