package com.mycompany.boundary;

import io.undertow.servlet.api.DeploymentInfo;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.SecurityContext;
import java.nio.file.attribute.UserPrincipal;
import java.util.LinkedHashSet;
import java.util.Set;

//import org.jboss.resteasy.api.validation.ResteasyViolationExceptionMapper;
//import org.jboss.resteasy.plugins.validation.ValidatorContextResolver;

/**
 * @author Guy Nouaga Youansi - guy.nouaga-youansi@movingimage24.com
 */
public abstract class AbstractResourceTest {

    private static UndertowJaxrsServer server;

    protected SecurityContext securityContext;
    protected UserPrincipal userPrincipal;

    @BeforeClass
    public static void init() throws Exception {
        server = new UndertowJaxrsServer();
        server.start();
    }

    @AfterClass
    public static void destroy() throws Exception {
        server.stop();
    }

    @Before
    public void before() {
//        securityContext = mock(SecurityContext.class);
//        userPrincipal = mock(UserPrincipal.class);
//        when(securityContext.getUserPrincipal()).thenReturn(userPrincipal);
//        when(userPrincipal.getName()).thenReturn("abc123");
        deploy();
    }

    protected void deploy() {
        ResourceTestApplication app = new ResourceTestApplication(prepareTestResource());

        ResteasyProviderFactory providerFactory = new ResteasyProviderFactory();

        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setProviderFactory(providerFactory);
        deployment.setApplication(app);

        DeploymentInfo deploymentInfo = server.undertowDeployment(deployment);
        deploymentInfo.setClassLoader(app.getClass().getClassLoader());
        deploymentInfo.setContextPath("/rest");
        deploymentInfo.setDeploymentName("RestEasy/rest");
        server.deploy(deploymentInfo);
    }

    /**
     * Prepares the rest resource and also other test resources.
     *
     * @return the prepared rest resource
     */
    public abstract Object prepareTestResource();

    private class ResourceTestApplication extends Application {

        private Object resource;

        public ResourceTestApplication(Object resource) {
            this.resource = resource;
        }

        @Override
        public Set<Object> getSingletons() {
            Set<Object> singletons = new LinkedHashSet<Object>();
            singletons.add(resource);
            return singletons;
        }
    }

//    private class PrincipalRequestFilter implements ContainerRequestFilter {
//
//        @Override
//        public void filter(ContainerRequestContext requestContext) throws IOException {
//            requestContext.setSecurityContext(securityContext);
//        }
//    }
}