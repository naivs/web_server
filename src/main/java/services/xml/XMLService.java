/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.xml;

import services.Service;

/**
 *
 * @author ivan
 */
public interface XMLService extends Service {
    
    public Object readXML(String xmlFile);
}
