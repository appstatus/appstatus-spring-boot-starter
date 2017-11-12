package net.sf.appstatus.boot.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.sf.appstatus.core.AppStatus;
import net.sf.appstatus.core.check.ICheck;
import net.sf.appstatus.core.property.IPropertyProvider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppStatusConfigTest {

    @Autowired
    private AppStatus appstatus;

    @Autowired
    private List<ICheck> checkers;

    @Autowired
    private List<IPropertyProvider> propertyProviders;

    @Test
    public void testNominal() {
        assertThat(appstatus).isNotNull();
        assertThat(checkers).isNotEmpty()
                            .size()
                            .isEqualTo(1);
        assertThat(propertyProviders).isNotEmpty()
                                     .size()
                                     .isEqualTo(3);
    }

}
