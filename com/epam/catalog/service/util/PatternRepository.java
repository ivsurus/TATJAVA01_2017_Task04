package com.epam.catalog.service.util;

import com.epam.catalog.service.EntityParameterName;
import java.util.HashMap;
import java.util.Map;

public final class PatternRepository {

        private final static  PatternRepository instance = new PatternRepository();

        private final Map<EntityParameterName, String> patternRepository = new HashMap<>();

        private PatternRepository() {
            patternRepository.put(EntityParameterName.TITLE, ServiceConstant.TITLE_PATTERN);
            patternRepository.put(EntityParameterName.AUTHOR, ServiceConstant.AUTHOR_PATTERN);
            patternRepository.put(EntityParameterName.YEAR, ServiceConstant.YEAR_PATTERN);
        }

        public static PatternRepository getInstance(){
            return instance;
        }

        public Map getPatternRepository(){
            return patternRepository;
        }

}
