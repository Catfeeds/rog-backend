package com.rograndec.feijiayun.chain.common.component;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhaiwei on 2017/3/21.
 */
@Configuration
public class OrikaMapperFactory {

    @Bean
    public MapperFactory getOrikaMapperFactory(){
        MapperFactory fa = new DefaultMapperFactory.Builder().build();
//        fa.registerClassMap(fa.classMap(SourceObj.class, DescObj.class)
                //设置正向空值不复制，反向空值不复制
//                .mapNulls(false).mapNullsInReverse(false)
        return fa;
    }


    public MapperFactory registerMap(Class sourceClazz , Class targetClazz){
        MapperFactory fa = getOrikaMapperFactory();
        fa.classMap(sourceClazz,targetClazz).mapNulls(false).mapNullsInReverse(false).byDefault().register();
        return fa;
    }

    public<S, D> D copyBean(D target,S source){
        MapperFactory mf = registerMap(source.getClass(),target.getClass());
        MapperFacade mapper = mf.getMapperFacade();
        target = (D) mapper.map(source,target.getClass());
        return target;
    }


//    private MapperFactory factory;
//
//    @Bean(initMethod="OrikaMapperFactoryInit")
//    public MapperFactory getOrikaMapperFactory(){
//        return factory;
//    }
//
//    public void OrikaMapperFactoryInit(){
//        MapperFactory fa = new DefaultMapperFactory.Builder().build();
//        factory = fa;
//    }
}

