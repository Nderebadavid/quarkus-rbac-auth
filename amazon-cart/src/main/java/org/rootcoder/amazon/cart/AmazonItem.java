package org.rootcoder.amazon.cart;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public record AmazonItem(Long id, String name, int quantity) {

    // Constructor taking a JsonObject
    public AmazonItem(JsonObject obj)
    {
        this(
                obj.getJsonNumber("id").longValue(),
                obj.getString("name"),
                obj.getInt("quantity")
        );
    }

}
