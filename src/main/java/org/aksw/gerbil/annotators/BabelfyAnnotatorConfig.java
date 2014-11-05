package org.aksw.gerbil.annotators;

import it.acubelab.batframework.problems.TopicSystem;
import it.acubelab.batframework.utils.WikipediaApiInterface;
import it.uniroma1.lcl.babelnet.BabelNetConfiguration;

import java.io.File;
import java.io.IOException;

import org.aksw.gerbil.bat.annotator.BabelfyAnnotator;
import org.aksw.gerbil.config.GerbilConfiguration;
import org.aksw.gerbil.datatypes.ExperimentType;

public class BabelfyAnnotatorConfig extends AbstractAnnotatorConfiguration {

    public static final String ANNOTATOR_NAME = "Babelfy";

    private static final String BABELNET_CONFIG_FILE_PROPERTY_NAME = "org.aksw.gerbil.annotators.BabelfyAnnotatorConfig.ConfigFile";
    private static final String BABELFY_WEB_SERVICE_KEY_PROPERTY_NAME = "org.aksw.gerbil.annotators.BabelfyAnnotatorConfig.Key";

    private WikipediaApiInterface wikiApi;

    public BabelfyAnnotatorConfig(WikipediaApiInterface wikiApi) {
        this(wikiApi, false);
    }

    public BabelfyAnnotatorConfig(WikipediaApiInterface wikiApi, boolean optimizedForShortTexts) {
        super(ANNOTATOR_NAME, true, ExperimentType.A2W);
        this.wikiApi = wikiApi;
    }

    @Override
    protected TopicSystem loadAnnotator(ExperimentType type) throws Exception {
        String configFile = GerbilConfiguration.getInstance().getString(BABELNET_CONFIG_FILE_PROPERTY_NAME);
        if (configFile == null) {
            throw new IOException("Couldn't load needed Property \"" + BABELNET_CONFIG_FILE_PROPERTY_NAME + "\".");
        }
        // Load the configuration
        BabelNetConfiguration.getInstance().setConfigurationFile(new File(configFile));

        // Load and use the key if there is one
        String key = GerbilConfiguration.getInstance().getString(BABELFY_WEB_SERVICE_KEY_PROPERTY_NAME);
        if (key == null) {
            return new BabelfyAnnotator(wikiApi);
        } else {
            return new BabelfyAnnotator(key, wikiApi);
        }
    }
}