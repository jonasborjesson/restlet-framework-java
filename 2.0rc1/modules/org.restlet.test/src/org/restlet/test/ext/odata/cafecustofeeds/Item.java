/**
 * Copyright 2005-2010 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.test.ext.odata.cafecustofeeds;




/**
* Generated by the generator tool for the WCF Data Services extension for the Restlet framework.<br>
*
* @see <a href="http://localhost:8182/CafeCustoFeeds.svc/$metadata">Metadata of the target WCF Data Services</a>
*
*/
public class Item {

private String description;
private String id;    /**
     * Constructor without parameter.
     * 
     */
    public Item() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param id
     *            The identifiant value of the entity.
     */
    public Item(String id) {
        this();
        this.id = id;
    }
    
   /**
    * Returns the value of the description attribute.
    *
    * @return The value of the description attribute.
    */
   public String getDescription() {
      return description;
   }
   
   /**
    * Returns the value of the id attribute.
    *
    * @return The value of the id attribute.
    */
   public String getId() {
      return id;
   }
   

   /**
    * Sets the value of the description attribute.
    *
    * @param Description
    *     The value of the description attribute.
    */
   public void setDescription(String description) {
      this.description = description;
   }
   
   /**
    * Sets the value of the id attribute.
    *
    * @param ID
    *     The value of the id attribute.
    */
   public void setId(String id) {
      this.id = id;
   }
   

}