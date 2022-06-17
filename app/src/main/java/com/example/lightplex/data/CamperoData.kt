package com.example.lightplex.data

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class CamperoData(val latitude: String? = null, val longitude: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}