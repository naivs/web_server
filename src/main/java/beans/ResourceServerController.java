/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import resources.ResourceServer;
import resources.TestResource;

/**
 *
 * @author ivan
 */
public class ResourceServerController implements ResourceServerControllerMBean {

    @Override
    public String getName() {
        TestResource testResource = (TestResource) ResourceServer.instance().getResource("resource.xml");
        return testResource.getName();
    }

    @Override
    public int getAge() {
        TestResource testResource = (TestResource) ResourceServer.instance().getResource("resource.xml");
        return testResource.getAge();
    }
}
