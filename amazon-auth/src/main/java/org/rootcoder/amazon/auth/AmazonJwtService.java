/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.rootcoder.amazon.auth;

import io.smallrye.jwt.build.Jwt;
import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author coder
 */
@ApplicationScoped
public class AmazonJwtService {

    Set<String> groups = new HashSet<>(
            Arrays.asList("admin", "user", "writer")
    );



    public String generateJwt()
    {
        return Jwt.issuer("amazon-jwt")
                .subject("amazon-jwt")
                .groups(groups)
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign();
    }




    var query = "SELECT "
            + "(SELECT JSON_OBJECT('productOwnerId', p.product_owner_id, 'aggregatorName', CONCAT(u.firstname,' ',u.lastname), 'email', u.email) FROM product_owners p JOIN  users u ON p.user_id=u.user_id WHERE product_owner_id = ?) AS aggregator, "
            + "(SELECT JSON_ARRAYAGG(JSON_OBJECT('storeName', s.store_name, 'storeId', s.store_id, 'location', l.location_name)) FROM stores s JOIN locations ON s.location_id=l.location_id WHERE s.product_owner_id=?) AS stores, "
            + "(SELECT JSON_ARRAYAGG(JSON_OBJECT('cropId', c.crop_id, 'createdAt', c.created_at, 'cropName', c.crop_name)) FROM crops c WHERE c.product_owner_id=?) AS crops, "
            + "(SELECT JSON_ARRAYAGG(JSON_OBJECT('farmerDetailsId', f.farmer_details_id, 'name', CONCAT(u.firstname.' ',u.lastname), 'gender', f.gender, 'msisdn', u.phone_number, 'email', u.email, 'username', u.username)) FROM farmer_details f JOIN users u ON f.user_id=u.user_id WHERE f.product_owner_id =?) AS farmers; ";


    var query1 = " SELECT"
            + "    (SELECT COUNT(crop_id) FROM farmer_details WHERE user_id = ?) AS total_crops,"
            + "    (SELECT COUNT(location_id) FROM farmer_details WHERE user_id = ?) AS total_locations,"
            + "    (SELECT COUNT(product_owner_id) FROM farmer_details WHERE user_id = ?) AS total_product_owners,"
            + "    (SELECT JSON_ARRAYAGG(JSON_OBJECT('productOwnerId', fd.product_owner_id, 'aggregatorName', CONCAT(pou.first_name, ' ',pou u.last_name), 'email', pou.email)) FROM farmer_details fd JOIN  product_owners po ON fd.product_owner_id =po.product_owner_id JOIN users pou ON po.user_id = pou.user_id WHERE fd.user_id = ?) AS aggregator,"
            + "    (SELECT JSON_ARRAYAGG(JSON_OBJECT(''locationId', fd.location_id, 'location', l.location_name)) FROM farmer_details fd JOIN locations l ON fd.location_id = l.location_id WHERE fd.user_id = ?) AS locations,"
            + "    (SELECT JSON_ARRAYAGG(JSON_OBJECT('cropId', fd.crop_id, 'createdAt', c.created_at, 'cropName', c.crop_name)) FROM  farmer_details fd JOIN crops c ON fd.crop_id =c.crop_id WHERE fd.use_id = ?) AS crops,"
            + "    (SELECT JSON_ARRAYAGG(JSON_OBJECT('farmerDetailsId', fd.farmer_details_id, 'name', CONCAT(u.first_name, ' ', u.last_name), 'gender', fd.gender,"
            + "                                      'msisdn', u.phone_number, 'email', u.email, 'username', u.username))"
            + "     FROM farmer_details fd JOIN users u ON fd.user_id = u.user_id WHERE fd.user_id = ?) AS farmer;";
}
