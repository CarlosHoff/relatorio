package br.com.hoffmann.relatorio.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@StepScope
public class RelatorioStepOneReader implements ItemReader<String> {
    private static final Logger LOG = LoggerFactory.getLogger(RelatorioStepOneReader.class);


    @Value("${arquivo.entrada}")
    private String arquivoEntrada;

    private ConcurrentLinkedQueue<String> queue;

    @BeforeStep
    public void readQueue(StepExecution stepExecution) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoEntrada)));

        String line;

        List<String> listfile = new ArrayList<>();

        try {
            while ((line = reader.readLine()) != null) {
                listfile.add(line);
            }
            queue = new ConcurrentLinkedQueue<>(listfile);
        } catch (Exception error) {
            LOG.info("Ocorreu algum erro ao tentar ler o Arquivo");
        } finally {
            reader.close();
        }
    }

    @Override
    public String read() {
        return queue.poll();
    }
}
