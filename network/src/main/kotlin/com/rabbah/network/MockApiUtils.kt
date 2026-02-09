package com.rabbah.network

object MockApiUtils {

    fun appVersion(): String {
        return """
            {
                "success": true,
                "message": "A new version is available for download",
                 "result": {
                    "is_forced_update": true,
                    "version_code": 2.0
                 }
            }
        """.trimIndent()
    }

    fun login(phoneNumber: String): String {
        return """
            {
                "success": true,
                "message": "success",
                "result": {
                    "is_blocked": false,
                    "message": "You are blocked, contact support"
                 }
            }
        """.trimIndent()
    }

    fun requestOtp(phoneNumber: String): String {
        return """
            {
                "success": true,
                "message": "an OTP is sent to your registered contact number"
            }
        """.trimIndent()
    }

    fun verifyOtp(userId: Int, otp: String): String {
        return """
            {
                "success": true,
                "message": "success",
                "result": {
                    "id": "123",
                    "name": "Rabbah Rabbah",
                    "phone": "0501234567",
                    "email": "dev@rabbah.com",
                    "address": "k-98",
                    "dateOfBirth": "01-01-1980",
                    "isActive": true,
                    "isProfileComplete": false,
                    "profileImage": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdwLvbM1DcDqatWEjIglrqEb1mPPCSa-c2SQ&s"
                 }
            }
        """.trimIndent()
    }

    fun homeOffer(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                "result":
                [ 
                    {
                        "offer_id": 301,
                        "image_url": "https://raw.githubusercontent.com/hfg-gmuend/openmoji/master/color/618x618/1F4E3.png",
                        "title":"Cashback 50%",
                        "description":"On your next purchase at any campus vending",
                        "expiry":"valid until 1 May 2025"
                    },
                    {
                        "offer_id": 302,
                        "image_url": "https://raw.githubusercontent.com/hfg-gmuend/openmoji/master/color/618x618/1F3F7.png",
                        "title":"Cashback 60%",
                        "description":"On your next purchase at any campus vending",
                        "expiry":"valid until 2 May 2025"
                    },
                    {
                        "offer_id": 303,
                        "image_url": "https://raw.githubusercontent.com/hfg-gmuend/openmoji/master/color/618x618/1F525.png",
                        "title":"Cashback 70%",
                        "description":"On your next purchase at any campus vending",
                        "expiry":"valid until 3 May 2025"
                    }
                ]
               
            }
        """.trimIndent()
    }

    fun updateAccountDetails(
        firstName: String,
        lastName: String,
        contactNumber: String,
        email: String
    ): String {
        return """
            {
                "success": true,
                "message": "success",
                "result": {
                    "id": 123,
                    "first_name": "New Rabbah",
                    "last_name": "KSA",
                    "contact_number": "+966507654321",
                    "email_address": "dev@rabbah.com",
                    "is_blocked": false,
                    "profile_image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdwLvbM1DcDqatWEjIglrqEb1mPPCSa-c2SQ&s"
                 }
            }
        """.trimIndent()
    }

    fun accountDetail(userId: Int): String {
        return """
            {
                "success": true,
                "message": "success",
                "result": {
                    "id": 123,
                    "first_name": "Rabbah",
                    "last_name": "KSA",
                    "contact_number": "+966507654321",
                    "email_address": "dev@rabbah.com",
                    "is_blocked": false,
                    "profile_image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdwLvbM1DcDqatWEjIglrqEb1mPPCSa-c2SQ&s"
                 }
            }
        """.trimIndent()
    }

    fun logout(userId: Int): String {
        return genericSuccessResponse("Successfully logged out")
    }

    // todo ask for multi part upload for profile picture
    fun updateProfilePicture(userId: Int, profilePictureUrl: String): String {
        return """
            {
                "success": true,
                "message": "success",
                "result": {
                    "profile_image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdwLvbM1DcDqatWEjIglrqEb1mPPCSa-c2SQ&s"
                 }
            }
        """.trimIndent()
    }

    fun activeOffers(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                "result": 
                 [ 
                   {
                        "offer_id": 301,
                        "image_url": "https://images.unsplash.com/photo-1607082349566-187342175e2f?auto=format&fit=crop&w=800&q=80",
                        "title":"Cashback 50%",
                        "description":"On your next purchase at any campus vending",
                        "expiry":"valid until 1 May 2025"
                    },
                    {
                        "offer_id": 302,
                        "image_url": "https://images.unsplash.com/photo-1522202222206-fc5c5b52ba5e?auto=format&fit=crop&w=800&q=80",
                        "title":"Cashback 50%",
                        "description":"On your next purchase at any campus vending",
                        "expiry":"valid until 1 May 2025"
                    }
                 ]
            }
        """.trimIndent()
    }

    fun expiredOffers(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                "result": 
                 [ 
                   {
                        "offer_id": 321,
                        "image_url": "https://images.unsplash.com/photo-1607082350315-d09d9b1b1d8a?auto=format&fit=crop&w=800&q=80",
                        "title":"Cashback 50%",
                        "description":"On your next purchase at any campus vending",
                        "expiry":"valid until 1 May 2025"
                    },
                    {
                        "offer_id": 322,
                        "image_url": "https://images.unsplash.com/photo-1543165796-5426273eaab7?auto=format&fit=crop&w=800&q=80",
                        "title":"Cashback 50%",
                        "description":"On your next purchase at any campus vending",
                        "expiry":"valid until 1 May 2025"
                    }
                 ]
            }
        """.trimIndent()
    }

    fun transactionsHistory(userId: Int, page: Int): String {
        return """
            {
                "success": true,
                "current_page": 1,
                "page_size": 20,
                "total_pages": 5,
                "total_items": 95,
                "has_next": true,
                "message": "",
                "result": 
                 [ 
    
                    {
                        "id": 101,
                        "status": true,
                        "date": "2026-11-13",
                        "grand_total": 1234.50,
                        "vend": {
                            "id": 123,
                            "latitude": 23.1009443,
                            "longitude": 40.6129168,
                            "title": "Title 1",
                            "address": "Address 1",
                            "distance": "Distance 1",
                            "available": true,
                            "image": "https://picsum.photos/seed/1/60/60"
                        },
                        "purchased_items": 
                        [
                            {
                                "id": 200,
                                "title": "Title 1",
                                "quantity": 1,
                                "unit_price": 25.00,
                                "image": "https://www.kindpng.com/picc/m/475-4756878_lays-chips-pack-png-image-lays-chips-packet.png"
                            },
                            {
                                "id": 201,
                                "title": "Title 2",
                                "quantity": 1,
                                "unit_price": 25.00,
                                "image": "https://www.kindpng.com/picc/m/475-4756878_lays-chips-pack-png-image-lays-chips-packet.png"
                            }
                        ]
                    },
                    {
                        "id": 101,
                        "status": true,
                        "date": "2026-11-13",
                        "grand_total": 1234.50,
                        "vend": {
                            "id": 123,
                            "latitude": 23.1009443,
                            "longitude": 40.6129168,
                            "title": "Title 1",
                            "address": "Address 1",
                            "distance": "Distance 1",
                            "available": true,
                            "image": "https://picsum.photos/seed/1/60/60"
                        },
                        "purchased_items": 
                        [
                            {
                                "id": 200,
                                "title": "Title 1",
                                "quantity": 1,
                                "unit_price": 25.00,
                                "image": "https://www.kindpng.com/picc/m/475-4756878_lays-chips-pack-png-image-lays-chips-packet.png"
                            },
                            {
                                "id": 201,
                                "title": "Title 2",
                                "quantity": 1,
                                "unit_price": 25.00,
                                "image": "https://www.kindpng.com/picc/m/475-4756878_lays-chips-pack-png-image-lays-chips-packet.png"
                            }
                        ]
                    },
                    {
                        "id": 101,
                        "status": true,
                        "date": "2026-11-13",
                        "grand_total": 1234.50,
                        "vend": {
                            "id": 123,
                            "latitude": 23.1009443,
                            "longitude": 40.6129168,
                            "title": "Title 1",
                            "address": "Address 1",
                            "distance": "Distance 1",
                            "available": true,
                            "image": "https://picsum.photos/seed/1/60/60"
                        },
                        "purchased_items": 
                        [
                            {
                                "id": 200,
                                "title": "Title 1",
                                "quantity": 1,
                                "unit_price": 25.00,
                                "image": "https://www.kindpng.com/picc/m/475-4756878_lays-chips-pack-png-image-lays-chips-packet.png"
                            },
                            {
                                "id": 201,
                                "title": "Title 2",
                                "quantity": 1,
                                "unit_price": 25.00,
                                "image": "https://www.kindpng.com/picc/m/475-4756878_lays-chips-pack-png-image-lays-chips-packet.png"
                            }
                        ]
                    }
                 ]
            }
        """
    }

    fun transactionDetails(userId: Int, transactionId: Int): String {
        return """
            {
               "success": true,
                "message": "",
                "result": {
                    "id": 101,
                    "grand_total": 100.00,
                    "status": true,
                    "date": "2026-11-13",
                    "purchased_items": 
                    [
                        {
                            "id": 200,
                            "title": "Title 1",
                            "quantity": 1,
                            "unit_price": 25.00,
                            "image": "https://www.kindpng.com/picc/m/475-4756878_lays-chips-pack-png-image-lays-chips-packet.png"
                        }
                    ]
                }
            }
        """.trimIndent()
    }

    fun nearbyVends(query: String, latitude: Double, longitude: Double, page: Int): String {
        return """
            {
                "success": true,
                "message": "",
                "result": 
                 [ 
                   {
                        "id": 123,
                        "latitude": 23.1009443,
                        "longitude": 40.6129168,
                        "title": "Title 1",
                        "address": "Address 1",
                        "distance": "Distance 1",
                        "available": true,
                        "image": "https://picsum.photos/seed/1/60/60"
                    },
                    {
                        "id": 124,
                        "latitude": 24.9182031,
                        "longitude": 46.6259595,
                        "title": "Title 2",
                        "address": "Address 2",
                        "distance": "Distance 2",
                        "available": false,
                        "image": "https://picsum.photos/seed/2/60/60"
                    },
                    {
                        "id": 125,
                        "latitude": 24.9182031,
                        "longitude": 46.6259595,
                        "title": "Title 3",
                        "address": "Address 3",
                        "distance": "Distance 3",
                        "available": true,
                        "image": "https://picsum.photos/seed/3/60/60"
                    },
                    {
                        "id": 126,
                        "latitude": 24.9182031,
                        "longitude": 46.6259595,
                        "title": "Title 4",
                        "address": "Address 4",
                        "distance": "Distance 4",
                        "available": true,
                        "image": "https://picsum.photos/seed/4/60/60"
                    }
                 ]
            }
        """.trimIndent()
    }

    fun wallet(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                 "result": {
                     "balance": 2500.75,
                     "is_card_requested": false,
                     "payment_gateway": "http://checkout.com/",
                     "card": {
                         "type": "visa",
                         "owner_name": "Rabbah KSA",
                         "expiry_date": "12/26",
                         "number": "1111222233334444",
                         "cvv": 123,
                         "is_locked": false,
                         "is_suspended": false
                    }
                }
            }
        """.trimIndent()
    }

    fun lockCard(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                 "result": {
                    "card_type": "visa",
                    "card_holder_name": "Rabbah",
                    "card_expiry_date": "12/26",
                    "card_number": "1111222233334444",
                    "is_card_locked": true,
                    "is_card_suspended": false
                 }
            }
        """.trimIndent()
    }

    fun unlockCard(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                 "result": {
                    "card_type": "visa",
                    "card_holder_name": "Rabbah",
                    "card_expiry_date": "12/26",
                    "card_number": "1111222233334444",
                    "is_card_locked": false,
                    "is_card_suspended": false
                 }
            }
        """.trimIndent()
    }

    fun activateCard(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                 "result": {
                    "card_type": "visa",
                    "card_holder_name": "Rabbah",
                    "card_expiry_date": "12/26",
                    "card_number": "1111222233334444",
                    "is_card_locked": false,
                    "is_card_suspended": false
                 }
            }
        """.trimIndent()
    }

    fun requestCard(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                 "result": {
                    "card_type": "visa",
                    "card_holder_name": "Rabbah",
                    "card_expiry_date": "12/26",
                    "card_number": "1111222233334444",
                    "is_card_locked": false,
                    "is_card_suspended": false
                 }
            }
        """.trimIndent()
    }

    // TODO pending
    fun topUpResponse(userId: Int): String {
        return """
            {
                "success": true,
                "message": "",
                 "result": {
           
                 }
            }
        """.trimIndent()
    }

    fun genericFailureResponse(message: String = "Something went wrong, please try again later"): String {
        return """
            {
                "success": false,
                "message": "$message"
            }
        """.trimIndent()
    }

    fun genericSuccessResponse(message: String = "Success"): String {
        return """
            {
                "success": true,
                "message": "$message"
            }
        """.trimIndent()
    }

}
