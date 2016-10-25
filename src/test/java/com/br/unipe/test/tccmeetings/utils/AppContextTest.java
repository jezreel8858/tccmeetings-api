package com.br.unipe.test.tccmeetings.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.br.unipe.tccmeetings.utils.AppContext;

@Configuration
@Import(value = { AppContext.class })
@ComponentScan(basePackages = { "com.br.unipe.test.tccmeetings" })
public abstract class AppContextTest {

}
