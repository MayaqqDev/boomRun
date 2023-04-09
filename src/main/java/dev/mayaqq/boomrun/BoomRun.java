package dev.mayaqq.boomrun;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoomRun implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("BoomRun");
    @Override
    public void onInitialize() {
        LOGGER.info("Boom Boom Boom Boom, I want you in my room!");
    }
}
