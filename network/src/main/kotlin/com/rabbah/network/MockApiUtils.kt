package com.rabbah.network

object MockApiUtils {
    fun brochures(): String {
        return """
           {
              "_embedded": {
                "contents": [
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": null,
                    "contentFormatSource": "destinationsApi",
                    "contentType": "superBannerCarousel",
                    "content": [
                      {
                        "content": {
                          "id": "6fa4bbcc-9ef4-4635-b219-22ff5fee7370",
                          "publisher": {
                            "id": "DE-92"
                          },
                          "publishedFrom": "2025-01-03T23:00:00.000+0000",
                          "publishedUntil": "2025-01-18T22:59:59.000+0000",
                          "clickUrl": "kaufda://slot/9fb799fb-a5ec-43ce-8d7b-9441a35fead7?pageType=superbanner",
                          "imageUrl": "https://content-media.bonial.biz/6fa4bbcc-9ef4-4635-b219-22ff5fee7370/c3e2d69f-c004-495c-9b28-544245cb5383.jpg"
                        },
                        "externalTracking": {
                          "impression": [],
                          "click": []
                        }
                      },
                      {
                        "content": {
                          "id": "6c814875-0e8b-4e92-a277-2e3f1eb1c045",
                          "publisher": {
                            "id": "DE-1024"
                          },
                          "publishedFrom": "2025-01-09T08:00:00.000+0000",
                          "publishedUntil": "2025-01-27T07:59:59.000+0000",
                          "clickUrl": "kaufda://slot/0d138fc0-6488-4e01-8735-a1d24828fdfb?pageType=superbanner",
                          "imageUrl": "https://content-media.bonial.biz/6c814875-0e8b-4e92-a277-2e3f1eb1c045/de04f14c-baa1-4f31-9947-53a8f0ffd554.jpg"
                        },
                        "externalTracking": {
                          "impression": [
                            "https://ad.doubleclick.net/ddm/trackimp/N195005.3097312BONIAL/B32950084.412578869;dc_trk_aid=604782435;dc_trk_cid=228540653;ord=$%7Btimestamp%7D;dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=;gdpr=$%7BGDPR%7D;gdpr_consent=$%7BGDPR_CONSENT_755%7D;ltd=;dc_tdv=1?"
                          ],
                          "click": [
                            "https://ad.doubleclick.net/ddm/trackclk/N195005.3097312BONIAL/B32950084.412578869;dc_trk_aid=604782435;dc_trk_cid=228540653;dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=;gdpr=${'$'}{GDPR};gdpr_consent=${'$'}{GDPR_CONSENT_755};ltd=;dc_tdv=1"
                          ]
                        }
                      }
                    ],
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_fixed_position_1",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500558744,
                      "contentId": "330dfb5c-b903-4fd4-9ae0-b196e0285e82",
                      "title": "LIDL LOHNT SICH",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T19:00:00.000+0000",
                      "publishedFrom": "2025-01-11T19:00:00.000+0000",
                      "publishedUntil": "2025-01-18T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/330dfb5c-b903-4fd4-9ae0-b196e0285e82/preview.jpg",
                      "pageCount": 55,
                      "publisher": {
                        "id": "DE-1013",
                        "name": "Lidl",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.9313949483394036,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-32993",
                        "latitude": 52.52431,
                        "longitude": 13.3795,
                        "street": "Luisenstraße",
                        "streetNumber": "52",
                        "zip": "10117",
                        "city": "Berlin-Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_fixed_position_2",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500562002,
                      "contentId": "a624c6c0-e5f3-49c7-9de4-d43c1a127628",
                      "title": "Dein Markt",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T23:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/a624c6c0-e5f3-49c7-9de4-d43c1a127628/preview.jpg",
                      "pageCount": 32,
                      "publisher": {
                        "id": "DE-1062",
                        "name": "REWE",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.6384821052822592,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1058434684",
                        "latitude": 52.5289,
                        "longitude": 13.3849,
                        "street": "Chausseestraße",
                        "streetNumber": "118-120",
                        "zip": "10115",
                        "city": "Berlin / Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://servedby.flashtalking.com/imp/2/263501;9130473;201;pixel;BonialInternationalDE;CPBonialBaseOffersAdgapId01984610141159891001/?gdpr=${'$'}{GDPR}&gdpr_consent=${'$'}{GDPR_CONSENT_78}&cachebuster=${'$'}{timestamp}"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500551243,
                      "contentId": "c8367075-df4b-47cb-9109-f1c09ed23903",
                      "title": "Handzettel KW 02",
                      "validFrom": "2025-01-03T23:00:00.000+0000",
                      "validUntil": "2025-02-18T22:59:00.000+0000",
                      "publishedFrom": "2025-01-03T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T22:59:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/c8367075-df4b-47cb-9109-f1c09ed23903/preview.jpg",
                      "pageCount": 11,
                      "publisher": {
                        "id": "DE-92",
                        "name": "BAUHAUS",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 3.9458234885126378,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-26531",
                        "latitude": 52.5037,
                        "longitude": 13.3439,
                        "street": "Bayreuther Straße",
                        "streetNumber": "3-4",
                        "zip": "10787",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500566431,
                      "contentId": "c49aefb0-3b46-4465-bb5e-cc8ab5483de7",
                      "title": "LIDL LOHNT SICH",
                      "validFrom": "2025-01-19T23:00:00.000+0000",
                      "validUntil": "2025-01-25T19:00:00.000+0000",
                      "publishedFrom": "2025-01-12T23:00:00.000+0000",
                      "publishedUntil": "2025-01-17T23:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/c49aefb0-3b46-4465-bb5e-cc8ab5483de7/preview.jpg",
                      "pageCount": 58,
                      "publisher": {
                        "id": "DE-1013",
                        "name": "Lidl",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.9313949483394036,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-32993",
                        "latitude": 52.52431,
                        "longitude": 13.3795,
                        "street": "Luisenstraße",
                        "streetNumber": "52",
                        "zip": "10117",
                        "city": "Berlin-Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560306,
                      "contentId": "813f8c0a-bd86-48b6-9a06-58b308312fa5",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T21:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T21:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/813f8c0a-bd86-48b6-9a06-58b308312fa5/preview.jpg",
                      "pageCount": 20,
                      "publisher": {
                        "id": "DE-220164",
                        "name": "EDEKA",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.22219767517052372,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-650918801",
                        "latitude": 52.5346,
                        "longitude": 13.3781,
                        "street": "Chausseestraße",
                        "streetNumber": "43",
                        "zip": "10115",
                        "city": "Berlin Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560298,
                      "contentId": "fe8aa22d-e1bb-4412-b03a-5b41008a3836",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T21:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T21:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/fe8aa22d-e1bb-4412-b03a-5b41008a3836/preview.jpg",
                      "pageCount": 24,
                      "publisher": {
                        "id": "DE-3443181",
                        "name": "E center",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 2.253718535080639,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-155580790",
                        "latitude": 52.532,
                        "longitude": 13.3444,
                        "street": "Stephanstraße",
                        "streetNumber": "37-43",
                        "zip": "10559",
                        "city": "Berlin Altmoabit"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564144,
                      "contentId": "ff4902d4-030e-4388-9caa-5b6d2393efaa",
                      "title": "Immer eine Idee besser",
                      "validFrom": "2025-01-11T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/ff4902d4-030e-4388-9caa-5b6d2393efaa/preview.jpg",
                      "pageCount": 15,
                      "publisher": {
                        "id": "DE-983",
                        "name": "Globus-Baumarkt",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 8.759748923988159,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-251836993",
                        "latitude": 52.5335,
                        "longitude": 13.5072,
                        "street": "Landsberger Allee",
                        "streetNumber": "360-362",
                        "zip": "10365",
                        "city": "Berlin-Lichtenberg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500562719,
                      "contentId": "b438c732-bf77-4ce5-aa0e-6b6164f356f2",
                      "title": "55 JAHRE - Galaktischer Schlussverkauf!",
                      "validFrom": "2025-01-11T23:00:00.000+0000",
                      "validUntil": "2025-01-25T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-25T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/b438c732-bf77-4ce5-aa0e-6b6164f356f2/preview.jpg",
                      "pageCount": 24,
                      "publisher": {
                        "id": "DE-1063",
                        "name": "ROLLER",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 14.284421758552183,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1659149513",
                        "latitude": 52.4167,
                        "longitude": 13.2868,
                        "street": "Am Stichkanal",
                        "streetNumber": "11",
                        "zip": "14167",
                        "city": "Berlin-Steglitz"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500558885,
                      "contentId": "1e76714a-6bdf-4cf8-b627-1507f30c8f93",
                      "title": "drogeriemarkt",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/1e76714a-6bdf-4cf8-b627-1507f30c8f93/preview.jpg",
                      "pageCount": 8,
                      "publisher": {
                        "id": "DE-219",
                        "name": "budni",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 1.6621623417821445,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1638068711",
                        "latitude": 52.5191,
                        "longitude": 13.3882,
                        "street": "Friedrichstraße",
                        "streetNumber": "149",
                        "zip": "10117",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500563137,
                      "contentId": "5838eb5f-16c7-4e86-8298-477dcbc65494",
                      "title": "NEUJAHRS SPARWOCHEN",
                      "validFrom": "2025-01-08T23:00:00.000+0000",
                      "validUntil": "2025-02-04T22:00:00.000+0000",
                      "publishedFrom": "2025-01-09T11:00:00.000+0000",
                      "publishedUntil": "2025-02-04T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/5838eb5f-16c7-4e86-8298-477dcbc65494/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-34905198",
                        "name": "Möbel Kraft",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.7667273185070762,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-588360637",
                        "latitude": 52.5485,
                        "longitude": 13.3783,
                        "street": "Pankstraße",
                        "streetNumber": "39",
                        "zip": "13357",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_premium",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochurePremium",
                    "content": {
                      "id": 2500562715,
                      "contentId": "af609f14-28b5-40d0-aa11-3bc4c45792ac",
                      "title": "LET'S GO! WSV",
                      "validFrom": "2025-01-09T08:00:00.000+0000",
                      "validUntil": "2025-01-27T07:59:00.000+0000",
                      "publishedFrom": "2025-01-09T08:00:00.000+0000",
                      "publishedUntil": "2025-01-27T07:59:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/af609f14-28b5-40d0-aa11-3bc4c45792ac/preview.jpg",
                      "pageCount": 16,
                      "publisher": {
                        "id": "DE-1024",
                        "name": "MediaMarkt Saturn",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.866480632171118,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-29148",
                        "latitude": 52.5494,
                        "longitude": 13.378,
                        "street": "Pankstraße",
                        "streetNumber": "32-39",
                        "zip": "13357",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560434,
                      "contentId": "16ed2c3e-a350-4082-b0bc-fe17777c2010",
                      "title": "kleine Preise, grosse Freude!",
                      "validFrom": "2025-01-07T23:00:00.000+0000",
                      "validUntil": "2025-01-14T22:00:00.000+0000",
                      "publishedFrom": "2025-01-08T13:30:00.000+0000",
                      "publishedUntil": "2025-01-14T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/16ed2c3e-a350-4082-b0bc-fe17777c2010/preview.jpg",
                      "pageCount": 31,
                      "publisher": {
                        "id": "DE-690854387",
                        "name": "Action",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "expiring_soon"
                        }
                      ],
                      "distance": 2.357279105085359,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000055341",
                        "latitude": 52.52687,
                        "longitude": 13.34416,
                        "street": "Turmstraße",
                        "streetNumber": "25",
                        "zip": "10559",
                        "city": "Berlin-Moabit"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560285,
                      "contentId": "df307f58-30d4-498c-b4da-6e83ba21bdbb",
                      "title": "150 JAHRE MÖBELPOWER",
                      "validFrom": "2025-01-07T23:00:00.000+0000",
                      "validUntil": "2025-01-21T22:00:00.000+0000",
                      "publishedFrom": "2025-01-07T23:00:00.000+0000",
                      "publishedUntil": "2025-01-21T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/df307f58-30d4-498c-b4da-6e83ba21bdbb/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-46660",
                        "name": "Höffner",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "popular"
                        }
                      ],
                      "distance": 6.5412855620743775,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-582486926",
                        "latitude": 52.4751,
                        "longitude": 13.3574,
                        "street": "Sachsendamm",
                        "streetNumber": "20",
                        "zip": "10829",
                        "city": "Berlin-Schöneberg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500552232,
                      "contentId": "a3d6ca22-a642-4695-a8b0-793edcf1a1d6",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2025-01-05T23:00:00.000+0000",
                      "validUntil": "2025-01-18T21:00:00.000+0000",
                      "publishedFrom": "2025-01-04T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T21:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/a3d6ca22-a642-4695-a8b0-793edcf1a1d6/preview.jpg",
                      "pageCount": 4,
                      "publisher": {
                        "id": "DE-1030",
                        "name": "Müller",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.1701895764569006,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-809268172",
                        "latitude": 52.547787,
                        "longitude": 13.357516,
                        "street": "Müllerstraße 30 A",
                        "streetNumber": "30A",
                        "zip": "13353",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500550992,
                      "contentId": "dce762e9-6b09-4a2d-b768-19bc12466d74",
                      "title": "Entdecke neue Geschmackshorizonte ganz ohne Fleisch",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-01T10:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/dce762e9-6b09-4a2d-b768-19bc12466d74/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1192589305",
                        "name": "AIDA",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 191.89039911089614,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1192605533",
                        "latitude": 54.0931,
                        "longitude": 12.1441,
                        "street": "Am Strande",
                        "streetNumber": "3d",
                        "zip": "18055",
                        "city": "Rostock"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://ad.doubleclick.net/ddm/trackclk/N410603.2041320BONIALINTERNATION/B32907079.411490558;dc_trk_aid=603747988;dc_trk_cid=227861870;dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=;gdpr=${'$'}{GDPR};gdpr_consent=${'$'}{GDPR_CONSENT_755};ltd=;dc_tdv=1",
                        "https://staex.aida.de/ai.aspx?extProvId=8&extPu=31180-dfa&extLi=32907079&extCr=227861870&extPm=411490558&url=https://ad.doubleclick.net/ddm/trackimp/N410603.2041320BONIALINTERNATION/B32907079.411490558;dc_trk_aid=603747988;dc_trk_cid=227861870;ord=${'$'}{timestamp};dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=;gdpr=${'$'}{GDPR};gdpr_consent=${'$'}{GDPR_CONSENT_755};ltd=;dc_tdv=1?"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500542796,
                      "contentId": "34c0ef12-2748-43ee-89e4-0e42cf5120cd",
                      "title": "JETZT BIS ZU 60% RABATT!",
                      "validFrom": "2024-12-26T23:00:00.000+0000",
                      "validUntil": "2025-01-28T22:00:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-28T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/34c0ef12-2748-43ee-89e4-0e42cf5120cd/preview.jpg",
                      "pageCount": 8,
                      "publisher": {
                        "id": "DE-1114127402",
                        "name": "Matratzen Concord",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.4495455133536352,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1114144776",
                        "latitude": 52.5362,
                        "longitude": 13.3571,
                        "street": "Ellen-Epstein-Straße",
                        "streetNumber": "2",
                        "zip": "10559",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500543825,
                      "contentId": "ea065641-5195-437c-825f-db76be7c60c0",
                      "title": "BIBERSTARKE ANGEBOTE",
                      "validFrom": "2024-12-22T23:00:00.000+0000",
                      "validUntil": "2025-01-15T22:59:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-15T22:59:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/ea065641-5195-437c-825f-db76be7c60c0/preview.jpg",
                      "pageCount": 20,
                      "publisher": {
                        "id": "DE-1042",
                        "name": "OBI",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "expiring_soon"
                        }
                      ],
                      "distance": 1.5751318167086736,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-15820330",
                        "latitude": 52.54174,
                        "longitude": 13.395517,
                        "street": "Demminer Straße",
                        "streetNumber": "31",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500543933,
                      "contentId": "715f601a-740e-4a01-834e-2b1462796a94",
                      "title": "WINTER VORRAT VERKAUF",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-15T19:00:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-15T19:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/715f601a-740e-4a01-834e-2b1462796a94/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-187862586",
                        "name": "Seats and Sofas",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "expiring_soon"
                        }
                      ],
                      "distance": 9.722707079041488,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-337134402",
                        "latitude": 52.5288,
                        "longitude": 13.5213,
                        "street": "Meeraner Straße",
                        "streetNumber": "6",
                        "zip": "12681",
                        "city": "Berlin Marzahn-Hellersdorf"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_premium",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochurePremium",
                    "content": {
                      "id": 2500564478,
                      "contentId": "6f3f42c3-0cd9-462d-8a67-177570dcf262",
                      "title": "Angebot des Monats",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-19T22:59:00.000+0000",
                      "publishedFrom": "2025-01-12T23:00:00.000+0000",
                      "publishedUntil": "2025-01-19T22:59:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/6f3f42c3-0cd9-462d-8a67-177570dcf262/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1002",
                        "name": "IKEA",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 7.059574646745533,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1874",
                        "latitude": 52.4695,
                        "longitude": 13.3664,
                        "street": "Sachsendamm",
                        "streetNumber": "47",
                        "zip": "10829",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://track.adform.net/adfserve/?bn=78034295;1x1inv=1;srctype=3;gdpr=${'$'}{GDPR};gdpr_consent=${'$'}{GDPR_CONSENT_50};ord=${'$'}{timestamp}"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_premium",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochurePremium",
                    "content": {
                      "id": 2500562772,
                      "contentId": "078ad90f-1efa-411d-820c-b02e333490d9",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T21:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-14T23:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/078ad90f-1efa-411d-820c-b02e333490d9/preview.jpg",
                      "pageCount": 1,
                      "publisher": {
                        "id": "DE-220164",
                        "name": "EDEKA",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.22219767517052372,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-650918801",
                        "latitude": 52.5346,
                        "longitude": 13.3781,
                        "street": "Chausseestraße",
                        "streetNumber": "43",
                        "zip": "10115",
                        "city": "Berlin Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560510,
                      "contentId": "73844fca-7e70-426e-9bf6-a586d7a1ab45",
                      "title": "MÖBELPOWER",
                      "validFrom": "2025-01-07T23:00:00.000+0000",
                      "validUntil": "2025-01-21T22:00:00.000+0000",
                      "publishedFrom": "2025-01-08T23:00:00.000+0000",
                      "publishedUntil": "2025-01-21T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/73844fca-7e70-426e-9bf6-a586d7a1ab45/preview.jpg",
                      "pageCount": 20,
                      "publisher": {
                        "id": "DE-46660",
                        "name": "Höffner",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 6.5412855620743775,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-582486926",
                        "latitude": 52.4751,
                        "longitude": 13.3574,
                        "street": "Sachsendamm",
                        "streetNumber": "20",
                        "zip": "10829",
                        "city": "Berlin-Schöneberg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500558720,
                      "contentId": "0bd18426-8cff-4e53-9863-ee544693ec6e",
                      "title": "NEUJAHRS SPAR-WOCHEN",
                      "validFrom": "2025-01-07T23:00:00.000+0000",
                      "validUntil": "2025-02-04T22:00:00.000+0000",
                      "publishedFrom": "2025-01-07T23:00:00.000+0000",
                      "publishedUntil": "2025-02-04T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/0bd18426-8cff-4e53-9863-ee544693ec6e/preview.jpg",
                      "pageCount": 7,
                      "publisher": {
                        "id": "DE-34905198",
                        "name": "Möbel Kraft",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.7667273185070762,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-588360637",
                        "latitude": 52.5485,
                        "longitude": 13.3783,
                        "street": "Pankstraße",
                        "streetNumber": "39",
                        "zip": "13357",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500552485,
                      "contentId": "18588f40-e0c2-48e6-83df-f1fd036db65d",
                      "title": "Auf ins Wintervergnügen",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-02-28T20:59:00.000+0000",
                      "publishedFrom": "2025-01-03T13:00:00.000+0000",
                      "publishedUntil": "2025-01-31T20:59:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/18588f40-e0c2-48e6-83df-f1fd036db65d/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-137419559",
                        "name": "Volkswagen",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 3.7934157770320858,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-137426205",
                        "latitude": 52.51896,
                        "longitude": 13.32632,
                        "street": "Franklinstraße",
                        "streetNumber": "5",
                        "zip": "10587",
                        "city": "Berlin-Charlottenburg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500558726,
                      "contentId": "1ea0ae63-31f0-49bf-b089-271c237547a2",
                      "title": "Genussvielfalt für Vollautomaten",
                      "validFrom": "2025-01-14T23:00:00.000+0000",
                      "validUntil": "2025-01-25T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-25T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/1ea0ae63-31f0-49bf-b089-271c237547a2/preview.jpg",
                      "pageCount": 2,
                      "publisher": {
                        "id": "DE-657872102",
                        "name": "Tchibo im Supermarkt",
                        "type": "MANUFACTURER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.7431737244874747,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1423760597",
                        "latitude": 52.5314,
                        "longitude": 13.3669,
                        "street": "Heidestraße",
                        "streetNumber": "41",
                        "zip": "10557",
                        "city": "Berlin / Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://track.adform.net/adfserve/?bn=78375549;1x1inv=1;srctype=3;gdpr=${'$'}{GDPR};gdpr_consent=${'$'}{GDPR_CONSENT_50};ord=${'$'}{timestamp"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500558850,
                      "contentId": "9749ad6a-c7a3-4fdc-a5d1-580ae794973f",
                      "title": "Guter Vorsatz: Ordentlich sauber",
                      "validFrom": "2025-01-14T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/9749ad6a-c7a3-4fdc-a5d1-580ae794973f/preview.jpg",
                      "pageCount": 32,
                      "publisher": {
                        "id": "DE-657872102",
                        "name": "Tchibo im Supermarkt",
                        "type": "MANUFACTURER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.7431737244874747,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1423760597",
                        "latitude": 52.5314,
                        "longitude": 13.3669,
                        "street": "Heidestraße",
                        "streetNumber": "41",
                        "zip": "10557",
                        "city": "Berlin / Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://track.adform.net/adfserve/?bn=78375098;1x1inv=1;srctype=3;gdpr=${'$'}{GDPR};gdpr_consent=${'$'}{GDPR_CONSENT_50};ord=${'$'}{timestamp}"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500545118,
                      "contentId": "50aa2dd1-4123-47e0-954c-48c3c7ad1ed6",
                      "title": "Filme und Serien ab 5,98€ mtl.",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-15T22:59:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/50aa2dd1-4123-47e0-954c-48c3c7ad1ed6/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-10000000034",
                        "name": "WOW",
                        "type": "MANUFACTURER"
                      },
                      "contentBadges": [],
                      "distance": 1.866480632171118,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-29148",
                        "latitude": 52.5494,
                        "longitude": 13.378,
                        "street": "Pankstraße",
                        "streetNumber": "32-39",
                        "zip": "13357",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://ad.doubleclick.net/ddm/trackimp/N1111979.2621703BONIALINTERNATIO/B32927531.411334433;dc_trk_aid=603449464;dc_trk_cid=227663231;ord=${'$'}{timestamp};dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=;gdpr=${'$'}{GDPR};gdpr_consent=${'$'}{GDPR_CONSENT_755};ltd=;dc_tdv=1?"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500546317,
                      "contentId": "f5d09ddb-e323-4767-8d44-0a1a3cca5706",
                      "title": "NEUJAHRS SPAR-WOCHEN",
                      "validFrom": "2025-01-01T23:00:00.000+0000",
                      "validUntil": "2025-01-21T22:00:00.000+0000",
                      "publishedFrom": "2025-01-01T23:00:00.000+0000",
                      "publishedUntil": "2025-01-21T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/f5d09ddb-e323-4767-8d44-0a1a3cca5706/preview.jpg",
                      "pageCount": 8,
                      "publisher": {
                        "id": "DE-34905198",
                        "name": "Möbel Kraft",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.7667273185070762,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-588360637",
                        "latitude": 52.5485,
                        "longitude": 13.3783,
                        "street": "Pankstraße",
                        "streetNumber": "39",
                        "zip": "13357",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500542710,
                      "contentId": "a7ad9773-5457-4bd4-8a68-9c1ea8fbafef",
                      "title": "KÜCHEN POWER",
                      "validFrom": "2024-12-22T23:00:00.000+0000",
                      "validUntil": "2025-01-16T22:00:00.000+0000",
                      "publishedFrom": "2024-12-27T23:00:00.000+0000",
                      "publishedUntil": "2025-01-16T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/a7ad9773-5457-4bd4-8a68-9c1ea8fbafef/preview.jpg",
                      "pageCount": 20,
                      "publisher": {
                        "id": "DE-46660",
                        "name": "Höffner",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "expiring_soon"
                        }
                      ],
                      "distance": 6.5412855620743775,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-582486926",
                        "latitude": 52.4751,
                        "longitude": 13.3574,
                        "street": "Sachsendamm",
                        "streetNumber": "20",
                        "zip": "10829",
                        "city": "Berlin-Schöneberg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560684,
                      "contentId": "23014b8f-3d16-4d18-ac72-57f669292865",
                      "title": "Dein Markt",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T23:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/23014b8f-3d16-4d18-ac72-57f669292865/preview.jpg",
                      "pageCount": 24,
                      "publisher": {
                        "id": "DE-1062",
                        "name": "REWE",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 1.5292219047158835,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-222484432",
                        "latitude": 52.5205,
                        "longitude": 13.3884,
                        "street": "Friedrichstraße",
                        "streetNumber": "100",
                        "zip": "10117",
                        "city": "Berlin / Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://servedby.flashtalking.com/imp/2/263501;9130473;201;pixel;BonialInternationalDE;CPBonialBaseOffersAdgapId01984610141159891001/?gdpr=${'$'}{GDPR}&gdpr_consent=${'$'}{GDPR_CONSENT_78}&cachebuster=${'$'}{timestamp}"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500562659,
                      "contentId": "f722211f-0643-44bb-9613-473ce502d1f6",
                      "title": "Dein Markt",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T23:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/f722211f-0643-44bb-9613-473ce502d1f6/preview.jpg",
                      "pageCount": 34,
                      "publisher": {
                        "id": "DE-1062",
                        "name": "REWE",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 2.060481121049537,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-779085411",
                        "latitude": 52.5498,
                        "longitude": 13.3891,
                        "street": "Badstraße",
                        "streetNumber": "4",
                        "zip": "13357",
                        "city": "Berlin / Gesundbrunnen"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://servedby.flashtalking.com/imp/2/263501;9130473;201;pixel;BonialInternationalDE;CPBonialBaseOffersAdgapId01984610141159891001/?gdpr=${'$'}{GDPR}&gdpr_consent=${'$'}{GDPR_CONSENT_78}&cachebuster=${'$'}{timestamp}"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500559720,
                      "contentId": "81c5f55c-931c-4353-81da-292e2c755fbc",
                      "title": "FUTTER & SNACKS AUS alternativen Proteinquellen",
                      "validFrom": "2024-11-29T23:00:00.000+0000",
                      "validUntil": "2025-01-24T22:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-20T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/81c5f55c-931c-4353-81da-292e2c755fbc/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-131228299",
                        "name": "Zookauf",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 7.555960064890907,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1221931112",
                        "latitude": 52.4792,
                        "longitude": 13.3087,
                        "street": "Forckenbeckstraße",
                        "streetNumber": "95",
                        "zip": "14199",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500559522,
                      "contentId": "528ad8db-b6cf-4743-805b-901ce59299b0",
                      "title": "ALLES WIRD TEURER, WIR BLEIBEN GÜNSTIG!",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-19T19:00:00.000+0000",
                      "publishedFrom": "2025-01-12T23:00:00.000+0000",
                      "publishedUntil": "2025-01-19T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/528ad8db-b6cf-4743-805b-901ce59299b0/preview.jpg",
                      "pageCount": 12,
                      "publisher": {
                        "id": "DE-1066",
                        "name": "SB Möbel Boss",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 8.599095496969674,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-100870518",
                        "latitude": 52.5397,
                        "longitude": 13.2511,
                        "street": "Nonnendammallee",
                        "streetNumber": "118",
                        "zip": "13629",
                        "city": "Berlin-Spandau"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564643,
                      "contentId": "7526abe5-b85c-4d51-bcce-2fbe1a538050",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T19:00:00.000+0000",
                      "publishedFrom": "2025-01-11T19:00:00.000+0000",
                      "publishedUntil": "2025-01-18T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/7526abe5-b85c-4d51-bcce-2fbe1a538050/preview.jpg",
                      "pageCount": 57,
                      "publisher": {
                        "id": "DE-1034",
                        "name": "Netto Marken-Discount",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 1.1945208425370437,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-82410798",
                        "latitude": 52.53231,
                        "longitude": 13.360051,
                        "street": "Lehrter Straße",
                        "streetNumber": "57",
                        "zip": "10557",
                        "city": "Berlin-Moabit-Moabit"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564243,
                      "contentId": "2e7ae4db-c742-4eed-ad6b-099633d6d8f2",
                      "title": "DER ORT, AN DEM DU IMMER AUSGEZEICHNETE PREISE FINDEST.",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T19:00:00.000+0000",
                      "publishedFrom": "2025-01-11T19:00:00.000+0000",
                      "publishedUntil": "2025-01-18T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/2e7ae4db-c742-4eed-ad6b-099633d6d8f2/preview.jpg",
                      "pageCount": 2,
                      "publisher": {
                        "id": "DE-1034",
                        "name": "Netto Marken-Discount",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 9.39093798561476,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-237411758",
                        "latitude": 52.59757,
                        "longitude": 13.466505,
                        "street": "Treseburger Straße",
                        "streetNumber": "29",
                        "zip": "13129",
                        "city": "Berlin-Blankenburg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500554683,
                      "contentId": "5d74f200-34a8-48c9-8d55-a49640dfa40c",
                      "title": "KÜCHEN ANGEBOTE",
                      "validFrom": "2025-01-05T23:00:00.000+0000",
                      "validUntil": "2025-02-02T19:00:00.000+0000",
                      "publishedFrom": "2025-01-05T23:00:00.000+0000",
                      "publishedUntil": "2025-02-02T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/5d74f200-34a8-48c9-8d55-a49640dfa40c/preview.jpg",
                      "pageCount": 12,
                      "publisher": {
                        "id": "DE-1066",
                        "name": "SB Möbel Boss",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 8.599095496969674,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-100870518",
                        "latitude": 52.5397,
                        "longitude": 13.2511,
                        "street": "Nonnendammallee",
                        "streetNumber": "118",
                        "zip": "13629",
                        "city": "Berlin-Spandau"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500543708,
                      "contentId": "b9bea124-b561-452b-b2d7-fc167f1e5312",
                      "title": "netto-online.de - Exklusive Angebote",
                      "validFrom": "2024-12-29T23:00:00.000+0000",
                      "validUntil": "2025-01-31T19:00:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/b9bea124-b561-452b-b2d7-fc167f1e5312/preview.jpg",
                      "pageCount": 40,
                      "publisher": {
                        "id": "DE-1034",
                        "name": "Netto Marken-Discount",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.1945208425370437,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-82410798",
                        "latitude": 52.53231,
                        "longitude": 13.360051,
                        "street": "Lehrter Straße",
                        "streetNumber": "57",
                        "zip": "10557",
                        "city": "Berlin-Moabit-Moabit"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564231,
                      "contentId": "85c3f5fc-4260-499d-ae3f-8e8b0b8a4d26",
                      "title": "SCONTOS JAHRESSTART KRACHER",
                      "validFrom": "2025-01-10T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/85c3f5fc-4260-499d-ae3f-8e8b0b8a4d26/preview.jpg",
                      "pageCount": 8,
                      "publisher": {
                        "id": "DE-26183",
                        "name": "Sconto SB",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 11.278309682490528,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-46707",
                        "latitude": 52.516151,
                        "longitude": 13.542199,
                        "street": "Märkische Spitze",
                        "streetNumber": "9",
                        "zip": "12681",
                        "city": "Berlin-Marzahn"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500543710,
                      "contentId": "6ef41698-ec8a-43b8-b047-b7f1734676ba",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/6ef41698-ec8a-43b8-b047-b7f1734676ba/preview.jpg",
                      "pageCount": 8,
                      "publisher": {
                        "id": "DE-1078",
                        "name": "VITALIA Reformhaus",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.8584458094945457,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-25908",
                        "latitude": 52.5173,
                        "longitude": 13.3887,
                        "street": "Friedrichstraße",
                        "streetNumber": "141",
                        "zip": "10117",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500567555,
                      "contentId": "55aca806-5542-4f99-a6d7-a2e1df482645",
                      "title": "Vegan Wine Pairing",
                      "validFrom": "2025-01-13T11:00:00.000+0000",
                      "validUntil": "2025-01-26T22:00:00.000+0000",
                      "publishedFrom": "2025-01-13T11:00:00.000+0000",
                      "publishedUntil": "2025-01-26T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/55aca806-5542-4f99-a6d7-a2e1df482645/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1369918583",
                        "name": "kaufDA Magazin",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 1.063510351549618,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1370106538",
                        "latitude": 52.5416,
                        "longitude": 13.3831,
                        "street": "Hussitenstraße",
                        "streetNumber": "32-33",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560441,
                      "contentId": "76f658c0-cdfa-428e-b2be-804c50ad96e6",
                      "title": "Super Bowl",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-02-10T22:00:00.000+0000",
                      "publishedFrom": "2025-01-12T23:00:00.000+0000",
                      "publishedUntil": "2025-02-10T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/76f658c0-cdfa-428e-b2be-804c50ad96e6/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1620034199",
                        "name": "Rezepte",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 1.065853369977459,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1739749536",
                        "latitude": 52.5416,
                        "longitude": 13.3832,
                        "street": "Hussitenstraße",
                        "streetNumber": " 32-33",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564485,
                      "contentId": "0afd4f32-da11-49bd-8925-500a6d03d1b1",
                      "title": "Mehr Rabatt. Mehr Sport.",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-16T22:59:00.000+0000",
                      "publishedFrom": "2025-01-12T23:00:00.000+0000",
                      "publishedUntil": "2025-01-16T22:59:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/0afd4f32-da11-49bd-8925-500a6d03d1b1/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-185262484",
                        "name": "Decathlon",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 2.0234552726389863,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1192334848",
                        "latitude": 52.5497,
                        "longitude": 13.388,
                        "street": "Badstraße",
                        "streetNumber": "4",
                        "zip": "13357",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500565905,
                      "contentId": "aec596e6-4395-4d36-b13c-c4ef1936966a",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/aec596e6-4395-4d36-b13c-c4ef1936966a/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.4007703399481735,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117660",
                        "latitude": 52.540548,
                        "longitude": 13.393795,
                        "street": "Brunnenstraße",
                        "streetNumber": "121",
                        "zip": "13355",
                        "city": "Berlin-Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500566393,
                      "contentId": "fa9cd3a6-05b2-4821-bb1f-36efa6ae791e",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/fa9cd3a6-05b2-4821-bb1f-36efa6ae791e/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.5101114861813312,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000116978",
                        "latitude": 52.544983,
                        "longitude": 13.368478,
                        "street": "Gerichtstraße",
                        "streetNumber": "31",
                        "zip": "13347",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500565800,
                      "contentId": "ac3d9b2f-1b45-4186-a9ba-b71859efb2f0",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/ac3d9b2f-1b45-4186-a9ba-b71859efb2f0/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.001185692758184,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117372",
                        "latitude": 52.550059,
                        "longitude": 13.384979,
                        "street": "Badstraße",
                        "streetNumber": "67",
                        "zip": "13357",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500566288,
                      "contentId": "e80fa7fa-7e54-47df-adb9-959ff72c60f6",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/e80fa7fa-7e54-47df-adb9-959ff72c60f6/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.010506633169318,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117457",
                        "latitude": 52.546425,
                        "longitude": 13.358514,
                        "street": "Müllerstraße",
                        "streetNumber": "152",
                        "zip": "13353",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500566158,
                      "contentId": "1076e869-928c-4e10-a312-3cfad3dca29f",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/1076e869-928c-4e10-a312-3cfad3dca29f/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.500834205070139,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117556",
                        "latitude": 52.51016,
                        "longitude": 13.379717,
                        "street": "Leipziger Platz",
                        "streetNumber": "12",
                        "zip": "10117",
                        "city": "Berlin-Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564940,
                      "contentId": "e2a58209-6ce1-4196-a36b-a1b7ab92a002",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/e2a58209-6ce1-4196-a36b-a1b7ab92a002/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.6009512767713203,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117725",
                        "latitude": 52.54103,
                        "longitude": 13.413585,
                        "street": "Danziger Straße",
                        "streetNumber": "5",
                        "zip": "10435",
                        "city": "Berlin (Pankow)"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500565709,
                      "contentId": "51127a4f-4e0c-4a82-865e-30df70ca7e82",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/51127a4f-4e0c-4a82-865e-30df70ca7e82/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.776247728163671,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000116349",
                        "latitude": 52.536074,
                        "longitude": 13.418353,
                        "street": "Kollwitzstraße",
                        "streetNumber": "66",
                        "zip": "10435",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500565664,
                      "contentId": "794af165-98e7-4521-80ac-28d0d2c2db11",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/794af165-98e7-4521-80ac-28d0d2c2db11/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.835478535286414,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117451",
                        "latitude": 52.527166,
                        "longitude": 13.336756,
                        "street": "Turmstraße",
                        "streetNumber": "38",
                        "zip": "10551",
                        "city": "Berlin-Moabit"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500565719,
                      "contentId": "438e12cd-3280-40c9-9d1b-89142ba7d1cd",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/438e12cd-3280-40c9-9d1b-89142ba7d1cd/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.883537514456348,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117365",
                        "latitude": 52.550932,
                        "longitude": 13.407885,
                        "street": "Schivelbeiner Straße",
                        "streetNumber": "33",
                        "zip": "10439",
                        "city": "Berlin - Prenzlauer Berg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564952,
                      "contentId": "44453a18-1427-4d44-a086-d75af3fc7e31",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/44453a18-1427-4d44-a086-d75af3fc7e31/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 3.0169179594443327,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117685",
                        "latitude": 52.547867,
                        "longitude": 13.414598,
                        "street": "Stargarder Straße",
                        "streetNumber": "79",
                        "zip": "10437",
                        "city": "Berlin-Prenzlauer Berg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500565428,
                      "contentId": "93d432d8-b246-4b5b-985f-fd54473f75a5",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/93d432d8-b246-4b5b-985f-fd54473f75a5/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 3.860332869585256,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117850",
                        "latitude": 52.498161,
                        "longitude": 13.384705,
                        "street": "Grossbeerenstraße",
                        "streetNumber": "11",
                        "zip": "10963",
                        "city": "Berlin-Kreuzberg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564927,
                      "contentId": "a508aa92-8d29-46f4-9e6d-d929a836a55e",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/a508aa92-8d29-46f4-9e6d-d929a836a55e/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 3.863032168110924,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117798",
                        "latitude": 52.517811,
                        "longitude": 13.42936,
                        "street": "Strausberger Platz",
                        "streetNumber": "11-12",
                        "zip": "10243",
                        "city": "Berlin-Friedrichshain"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500565185,
                      "contentId": "bd0d59f2-6b07-4e48-8ff3-c0daf3fe05e6",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/bd0d59f2-6b07-4e48-8ff3-c0daf3fe05e6/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 3.8774704950818544,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000116610",
                        "latitude": 52.548759,
                        "longitude": 13.428524,
                        "street": "Prenzlauer Allee",
                        "streetNumber": "146",
                        "zip": "10409",
                        "city": "Berlin - Prenzlauer Berg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500566095,
                      "contentId": "8eb6c152-e870-4bbb-9467-a59212618afd",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/8eb6c152-e870-4bbb-9467-a59212618afd/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 3.9166506033549853,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117737",
                        "latitude": 52.535407,
                        "longitude": 13.435427,
                        "street": "Danziger Straße",
                        "streetNumber": "133",
                        "zip": "10407",
                        "city": "Berlin (Pankow)"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500566069,
                      "contentId": "72550133-044a-4eda-a2dc-0d293ed07235",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/72550133-044a-4eda-a2dc-0d293ed07235/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 4.311817516840558,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000116715",
                        "latitude": 52.503648,
                        "longitude": 13.335342,
                        "street": "Rankestraße",
                        "streetNumber": "34",
                        "zip": "10789",
                        "city": "Berlin-Charlottenburg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500566166,
                      "contentId": "8325fcd4-171c-453f-bcb5-1d39212c5202",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/8325fcd4-171c-453f-bcb5-1d39212c5202/preview.jpg",
                      "pageCount": 3,
                      "publisher": {
                        "id": "DE-10000002076",
                        "name": "LINDA Partnerapotheke",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 4.618371507416385,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000117958",
                        "latitude": 52.497927,
                        "longitude": 13.340164,
                        "street": "Ansbacher Straße",
                        "streetNumber": "53",
                        "zip": "10777",
                        "city": "Berlin-Schöneberg"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500563179,
                      "contentId": "ad4d5a1f-7234-4e05-b8de-8ad5845ea463",
                      "title": "Dry January",
                      "validFrom": "2025-01-09T23:00:00.000+0000",
                      "validUntil": "2025-02-03T22:00:00.000+0000",
                      "publishedFrom": "2025-01-09T23:00:00.000+0000",
                      "publishedUntil": "2025-02-03T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/ad4d5a1f-7234-4e05-b8de-8ad5845ea463/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1620034199",
                        "name": "Rezepte",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.065853369977459,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1739749536",
                        "latitude": 52.5416,
                        "longitude": 13.3832,
                        "street": "Hussitenstraße",
                        "streetNumber": " 32-33",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500562703,
                      "contentId": "87f48d15-85b7-42d1-93c0-2d3c08815468",
                      "title": "Die Portopreise auf einen Blick",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-06-30T21:00:00.000+0000",
                      "publishedFrom": "2025-01-08T13:00:00.000+0000",
                      "publishedUntil": "2025-06-30T21:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/87f48d15-85b7-42d1-93c0-2d3c08815468/preview.jpg",
                      "pageCount": 1,
                      "publisher": {
                        "id": "DE-341945975",
                        "name": "Deutsche Post",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 0.4230551226477001,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-342649436",
                        "latitude": 52.5308,
                        "longitude": 13.3832,
                        "street": "Invalidenstraße",
                        "streetNumber": "35",
                        "zip": "10115",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500558934,
                      "contentId": "2c918f7f-09ec-441a-a5ea-317d844c46b1",
                      "title": "Vegane Lebensmittel",
                      "validFrom": "2025-01-07T23:00:00.000+0000",
                      "validUntil": "2025-02-05T22:00:00.000+0000",
                      "publishedFrom": "2025-01-07T23:00:00.000+0000",
                      "publishedUntil": "2025-02-05T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/2c918f7f-09ec-441a-a5ea-317d844c46b1/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1369918583",
                        "name": "kaufDA Magazin",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.063510351549618,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1370106538",
                        "latitude": 52.5416,
                        "longitude": 13.3831,
                        "street": "Hussitenstraße",
                        "streetNumber": "32-33",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500551501,
                      "contentId": "418d278e-9c3d-45d1-9c88-3ba3808452d8",
                      "title": "No Sugar",
                      "validFrom": "2025-01-05T23:00:00.000+0000",
                      "validUntil": "2025-01-27T22:00:00.000+0000",
                      "publishedFrom": "2025-01-05T23:00:00.000+0000",
                      "publishedUntil": "2025-01-27T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/418d278e-9c3d-45d1-9c88-3ba3808452d8/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1620034199",
                        "name": "Rezepte",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.065853369977459,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1739749536",
                        "latitude": 52.5416,
                        "longitude": 13.3832,
                        "street": "Hussitenstraße",
                        "streetNumber": " 32-33",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500551499,
                      "contentId": "6f8f49e1-fd59-4b86-a9c4-82c33aecf20c",
                      "title": "Fitness Rezepte",
                      "validFrom": "2025-01-02T23:00:00.000+0000",
                      "validUntil": "2025-02-03T22:00:00.000+0000",
                      "publishedFrom": "2025-01-02T23:00:00.000+0000",
                      "publishedUntil": "2025-02-03T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/6f8f49e1-fd59-4b86-a9c4-82c33aecf20c/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1620034199",
                        "name": "Rezepte",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.065853369977459,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1739749536",
                        "latitude": 52.5416,
                        "longitude": 13.3832,
                        "street": "Hussitenstraße",
                        "streetNumber": " 32-33",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500543723,
                      "contentId": "57e83315-c6a0-4a0a-8b55-64a370dc74fb",
                      "title": "Veganuary Ratgeber",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/57e83315-c6a0-4a0a-8b55-64a370dc74fb/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1369918583",
                        "name": "kaufDA Magazin",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.063510351549618,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1370106538",
                        "latitude": 52.5416,
                        "longitude": 13.3831,
                        "street": "Hussitenstraße",
                        "streetNumber": "32-33",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500543720,
                      "contentId": "633f592f-3f6f-4b55-ac64-25eaeb90ad9f",
                      "title": "Veganuary Rezepte",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/633f592f-3f6f-4b55-ac64-25eaeb90ad9f/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1620034199",
                        "name": "Rezepte",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.065853369977459,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1739749536",
                        "latitude": 52.5416,
                        "longitude": 13.3832,
                        "street": "Hussitenstraße",
                        "streetNumber": " 32-33",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500476529,
                      "contentId": "ba2b65f3-9476-4b15-a6ea-0847d78df3a5",
                      "title": "Schenken Sie Gesundheit!",
                      "validFrom": "2024-11-12T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2024-11-12T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/ba2b65f3-9476-4b15-a6ea-0847d78df3a5/preview.jpg",
                      "pageCount": 6,
                      "publisher": {
                        "id": "DE-760954675",
                        "name": "Hempel GesundheitsPartner GmbH",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.3815238304434647,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-760954746",
                        "latitude": 52.5539,
                        "longitude": 13.3816,
                        "street": "Prinzenallee",
                        "streetNumber": "84",
                        "zip": "13357",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500563321,
                      "contentId": "1f5895b9-2472-4bd6-b650-76b2e79269c7",
                      "title": "KNÜLLER",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-15T19:00:00.000+0000",
                      "publishedFrom": "2025-01-12T17:00:00.000+0000",
                      "publishedUntil": "2025-01-15T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/1f5895b9-2472-4bd6-b650-76b2e79269c7/preview.jpg",
                      "pageCount": 56,
                      "publisher": {
                        "id": "DE-424316869",
                        "name": "Kaufland",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 1.8913961804905348,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-424320339",
                        "latitude": 52.547972,
                        "longitude": 13.389726,
                        "street": "Brunnenstraße",
                        "streetNumber": "105-109",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://ad.doubleclick.net/ddm/trackimp/N1106503.3097312BONIAL/B26240841.324173224;dc_trk_aid=516404875;dc_trk_cid=163515427;ord=${'$'}{timestamp};dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=;gdpr=;gdpr_consent=;ltd=?"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564385,
                      "contentId": "18595fb7-8664-478c-bd46-39bea342a3f7",
                      "title": "Wer günstig will, muss Penny.",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T23:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T23:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/18595fb7-8664-478c-bd46-39bea342a3f7/preview.jpg",
                      "pageCount": 34,
                      "publisher": {
                        "id": "DE-1050",
                        "name": "Penny",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 1.154917847752398,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1332778386",
                        "latitude": 52.543,
                        "longitude": 13.3781,
                        "street": "Gerichtstraße",
                        "streetNumber": "4",
                        "zip": "13347",
                        "city": "Berlin-Gesundbrunnen"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://servedby.flashtalking.com/imp/2/262944;9113229;201;pixel;BonialInternationalDE;CPBonialProspektAdgapId01984610141165977001/?gdpr=${'$'}{GDPR}&gdpr_consent=${'$'}{GDPR_CONSENT_78}&cachebuster=${'$'}{timestamp}"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564226,
                      "contentId": "0d07d164-b000-46c7-a1aa-bfe8cd42a590",
                      "title": "Coole Preise - mit POCO perfekt durch den Winter",
                      "validFrom": "2025-01-11T23:00:00.000+0000",
                      "validUntil": "2025-01-25T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-25T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/0d07d164-b000-46c7-a1aa-bfe8cd42a590/preview.jpg",
                      "pageCount": 16,
                      "publisher": {
                        "id": "DE-1052",
                        "name": "POCO",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 2.8906304153991065,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-172861",
                        "latitude": 52.5586,
                        "longitude": 13.3765,
                        "street": "Drontheimer Straße",
                        "streetNumber": "30a",
                        "zip": "13359",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564225,
                      "contentId": "588cff0b-42af-47f9-af35-d1a33d2862cd",
                      "title": "Traumhafte Preise - Groß-Elektro bei POCO",
                      "validFrom": "2025-01-11T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/588cff0b-42af-47f9-af35-d1a33d2862cd/preview.jpg",
                      "pageCount": 6,
                      "publisher": {
                        "id": "DE-1052",
                        "name": "POCO",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 2.8906304153991065,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-172861",
                        "latitude": 52.5586,
                        "longitude": 13.3765,
                        "street": "Drontheimer Straße",
                        "streetNumber": "30a",
                        "zip": "13359",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_premium",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochurePremium",
                    "content": {
                      "id": 2500558598,
                      "contentId": "d5aafcc7-b961-4c09-85ea-04366ea1160a",
                      "title": "Günstig. Besser. Für Dich.",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T21:00:00.000+0000",
                      "publishedFrom": "2025-01-11T21:00:00.000+0000",
                      "publishedUntil": "2025-01-18T21:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/d5aafcc7-b961-4c09-85ea-04366ea1160a/preview.jpg",
                      "pageCount": 37,
                      "publisher": {
                        "id": "DE-1122",
                        "name": "Netto mit dem Scottie",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 1.3143719778632128,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-32544",
                        "latitude": 52.5424,
                        "longitude": 13.3668,
                        "street": "Müllerstraße",
                        "streetNumber": "10-11",
                        "zip": "13353",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500562769,
                      "contentId": "be399996-8d9c-4931-a435-4629eacd2dbc",
                      "title": "Top Angebote",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-18T19:00:00.000+0000",
                      "publishedFrom": "2025-01-11T07:00:00.000+0000",
                      "publishedUntil": "2025-01-18T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/be399996-8d9c-4931-a435-4629eacd2dbc/preview.jpg",
                      "pageCount": 10,
                      "publisher": {
                        "id": "DE-118563",
                        "name": "Thomas Philipps",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 4.28803598552117,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-416502554",
                        "latitude": 52.5701,
                        "longitude": 13.3628,
                        "street": "Friedrich-Wilhelm-Straße",
                        "streetNumber": "81-83",
                        "zip": "13409",
                        "city": "Berlin - Reinickendorf"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564217,
                      "contentId": "5d5f0538-544e-4d94-b440-d77f516a98ef",
                      "title": "Unsere Angebote",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-17T21:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-17T21:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/5d5f0538-544e-4d94-b440-d77f516a98ef/preview.jpg",
                      "pageCount": 17,
                      "publisher": {
                        "id": "DE-1064",
                        "name": "Rossmann",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.7201108822986355,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1167794079",
                        "latitude": 52.527,
                        "longitude": 13.3724,
                        "street": "Invalidenstraße",
                        "streetNumber": "73",
                        "zip": "10557",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500564216,
                      "contentId": "e4585ba1-3234-45f3-8cca-706c346f42e2",
                      "title": "Online exklusive Angebote",
                      "validFrom": "2025-01-12T23:00:00.000+0000",
                      "validUntil": "2025-01-24T21:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-17T21:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/e4585ba1-3234-45f3-8cca-706c346f42e2/preview.jpg",
                      "pageCount": 6,
                      "publisher": {
                        "id": "DE-1064",
                        "name": "Rossmann",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "new"
                        }
                      ],
                      "distance": 0.7201108822986355,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1167794079",
                        "latitude": 52.527,
                        "longitude": 13.3724,
                        "street": "Invalidenstraße",
                        "streetNumber": "73",
                        "zip": "10557",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560528,
                      "contentId": "e1f2d150-0fa9-46e0-8ee7-4287e4e938aa",
                      "title": "Schlafen wie auf Wolken",
                      "validFrom": "2025-01-10T23:00:00.000+0000",
                      "validUntil": "2025-01-24T22:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-24T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/e1f2d150-0fa9-46e0-8ee7-4287e4e938aa/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1052",
                        "name": "POCO",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.8906304153991065,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-172861",
                        "latitude": 52.5586,
                        "longitude": 13.3765,
                        "street": "Drontheimer Straße",
                        "streetNumber": "30a",
                        "zip": "13359",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500562714,
                      "contentId": "f5fa31b7-2505-44bb-97ea-1aa3b7370445",
                      "title": "WSV - bis zu 66% unter UVP",
                      "validFrom": "2025-01-10T23:00:00.000+0000",
                      "validUntil": "2025-01-17T22:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-17T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/f5fa31b7-2505-44bb-97ea-1aa3b7370445/preview.jpg",
                      "pageCount": 48,
                      "publisher": {
                        "id": "DE-1052",
                        "name": "POCO",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 2.8906304153991065,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-172861",
                        "latitude": 52.5586,
                        "longitude": 13.3765,
                        "street": "Drontheimer Straße",
                        "streetNumber": "30a",
                        "zip": "13359",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500562859,
                      "contentId": "10342910-d01c-4959-90b8-1c007395febd",
                      "title": "Respekt, wer's selber macht.",
                      "validFrom": "2025-01-10T23:00:00.000+0000",
                      "validUntil": "2025-01-17T22:00:00.000+0000",
                      "publishedFrom": "2025-01-10T23:00:00.000+0000",
                      "publishedUntil": "2025-01-17T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/10342910-d01c-4959-90b8-1c007395febd/preview.jpg",
                      "pageCount": 8,
                      "publisher": {
                        "id": "DE-23108525",
                        "name": "toom Baumarkt",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 6.0853176955395485,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-663015549",
                        "latitude": 52.5228,
                        "longitude": 13.4662,
                        "street": "Hermann-Blankenstein-Straße",
                        "streetNumber": "44",
                        "zip": "10249",
                        "city": "Berlin-Friedrichshain"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://servedby.flashtalking.com/imp/2/262518;9094568;201;pixel;BonialInternationalDE;CPBonialBaseLinkoutsAdgapId01684610141164521001/?gdpr=${'$'}{GDPR}&gdpr_consent=${'$'}{GDPR_CONSENT_78}&cachebuster=${'$'}{timestamp}"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500560117,
                      "contentId": "afe56d34-ccd6-465c-811f-8b209c9fc6d3",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2025-01-08T23:00:00.000+0000",
                      "validUntil": "2025-01-15T19:00:00.000+0000",
                      "publishedFrom": "2025-01-08T19:00:00.000+0000",
                      "publishedUntil": "2025-01-15T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/afe56d34-ccd6-465c-811f-8b209c9fc6d3/preview.jpg",
                      "pageCount": 56,
                      "publisher": {
                        "id": "DE-424316869",
                        "name": "Kaufland",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "expiring_soon"
                        }
                      ],
                      "distance": 1.8913961804905348,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-424320339",
                        "latitude": 52.547972,
                        "longitude": 13.389726,
                        "street": "Brunnenstraße",
                        "streetNumber": "105-109",
                        "zip": "13355",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://ad.doubleclick.net/ddm/trackimp/N1106503.3097312BONIAL/B26240841.324173224;dc_trk_aid=516404875;dc_trk_cid=163515427;ord=${'$'}{timestamp};dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=;gdpr=;gdpr_consent=;ltd=?"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500559733,
                      "contentId": "8d13c3f7-c0ba-4209-8783-3ffa30cc048d",
                      "title": "Sonderbeilage",
                      "validFrom": "2025-01-08T23:00:00.000+0000",
                      "validUntil": "2025-01-15T19:00:00.000+0000",
                      "publishedFrom": "2025-01-08T19:00:00.000+0000",
                      "publishedUntil": "2025-01-15T19:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/8d13c3f7-c0ba-4209-8783-3ffa30cc048d/preview.jpg",
                      "pageCount": 8,
                      "publisher": {
                        "id": "DE-424316869",
                        "name": "Kaufland",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "expiring_soon"
                        }
                      ],
                      "distance": 5.761530761296484,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-424319424",
                        "latitude": 52.564331,
                        "longitude": 13.445086,
                        "street": "Romain-Rolland-Straße",
                        "streetNumber": "13",
                        "zip": "13089",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500559684,
                      "contentId": "e7194e88-400c-4083-901b-f78385eed6ea",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2025-01-08T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-07T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/e7194e88-400c-4083-901b-f78385eed6ea/preview.jpg",
                      "pageCount": 30,
                      "publisher": {
                        "id": "DE-1080",
                        "name": "Woolworth",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.905659778021823,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-545347567",
                        "latitude": 52.5458,
                        "longitude": 13.3597,
                        "street": "Müllerstraße",
                        "streetNumber": "153A-154",
                        "zip": "13353",
                        "city": "Berlin-Wedding"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500558769,
                      "contentId": "d884350f-1a28-4efe-ba82-0b002cf6d060",
                      "title": "Aktuelle Angebote",
                      "validFrom": "2025-01-06T23:00:00.000+0000",
                      "validUntil": "2025-01-15T22:00:00.000+0000",
                      "publishedFrom": "2025-01-06T23:00:00.000+0000",
                      "publishedUntil": "2025-01-15T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/d884350f-1a28-4efe-ba82-0b002cf6d060/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-909",
                        "name": "dm-drogerie markt",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "expiring_soon"
                        }
                      ],
                      "distance": 0.1734943468949532,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1127699225",
                        "latitude": 52.533875,
                        "longitude": 13.379219,
                        "street": "Chausseestraße",
                        "streetNumber": "40",
                        "zip": "10115",
                        "city": "Berlin-Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500558717,
                      "contentId": "a8853080-6a7f-4ef2-8ea2-a2a50174cea6",
                      "title": "Zirkulin Vital - Mikronährstoffkonzentrat",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-02-15T22:00:00.000+0000",
                      "publishedFrom": "2025-01-06T11:00:00.000+0000",
                      "publishedUntil": "2025-02-15T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/a8853080-6a7f-4ef2-8ea2-a2a50174cea6/preview.jpg",
                      "pageCount": 1,
                      "publisher": {
                        "id": "DE-10000002086",
                        "name": "Zirkulin",
                        "type": "MANUFACTURER"
                      },
                      "contentBadges": [],
                      "distance": 0.7201108822986355,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1167794079",
                        "latitude": 52.527,
                        "longitude": 13.3724,
                        "street": "Invalidenstraße",
                        "streetNumber": "73",
                        "zip": "10557",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500543717,
                      "contentId": "86a3a87b-dca5-40c7-bbfa-a18a18c876f8",
                      "title": "SPARE BIS ZU 70%",
                      "validFrom": "2024-12-30T23:00:00.000+0000",
                      "validUntil": "2025-02-15T22:59:00.000+0000",
                      "publishedFrom": "2025-01-04T23:00:00.000+0000",
                      "publishedUntil": "2025-02-15T22:59:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/86a3a87b-dca5-40c7-bbfa-a18a18c876f8/preview.jpg",
                      "pageCount": 17,
                      "publisher": {
                        "id": "DE-258",
                        "name": "JYSK",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 1.963080915815889,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-432669547",
                        "latitude": 52.5491,
                        "longitude": 13.3881,
                        "street": "Badstraße",
                        "streetNumber": "4",
                        "zip": "13357",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500554722,
                      "contentId": "3bf3db8b-32f0-4fa4-a254-44116fc15892",
                      "title": "BESSER GLEICH ZU PORTA",
                      "validFrom": "2025-01-04T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-04T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/3bf3db8b-32f0-4fa4-a254-44116fc15892/preview.jpg",
                      "pageCount": 10,
                      "publisher": {
                        "id": "DE-34906483",
                        "name": "porta Möbel",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 16.930917857443227,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-679197138",
                        "latitude": 52.5011,
                        "longitude": 13.6225,
                        "street": "Alt-Mahlsdorf",
                        "streetNumber": "85",
                        "zip": "12623",
                        "city": "Berlin-Mahlsdorf"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500554720,
                      "contentId": "f7d9cafa-a21d-4b3f-a1fe-25049b651056",
                      "title": "BESSER GLEICH ZU PORTA",
                      "validFrom": "2025-01-04T23:00:00.000+0000",
                      "validUntil": "2025-01-18T22:00:00.000+0000",
                      "publishedFrom": "2025-01-04T23:00:00.000+0000",
                      "publishedUntil": "2025-01-18T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/f7d9cafa-a21d-4b3f-a1fe-25049b651056/preview.jpg",
                      "pageCount": 4,
                      "publisher": {
                        "id": "DE-34906483",
                        "name": "porta Möbel",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 16.930917857443227,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-679197138",
                        "latitude": 52.5011,
                        "longitude": 13.6225,
                        "street": "Alt-Mahlsdorf",
                        "streetNumber": "85",
                        "zip": "12623",
                        "city": "Berlin-Mahlsdorf"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500547754,
                      "contentId": "044611a2-9ce5-4b15-bdb4-9e9cf9f95d9b",
                      "title": "PREIS HIT!",
                      "validFrom": "2025-01-01T23:00:00.000+0000",
                      "validUntil": "2025-01-15T22:59:00.000+0000",
                      "publishedFrom": "2025-01-01T23:00:00.000+0000",
                      "publishedUntil": "2025-01-15T22:59:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/044611a2-9ce5-4b15-bdb4-9e9cf9f95d9b/preview.jpg",
                      "pageCount": 12,
                      "publisher": {
                        "id": "DE-1683842705",
                        "name": "PEPCO",
                        "type": "RETAILER"
                      },
                      "contentBadges": [
                        {
                          "name": "expiring_soon"
                        }
                      ],
                      "distance": 2.3389014246665445,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-2035767387",
                        "latitude": 52.5288,
                        "longitude": 13.3437,
                        "street": "Turmstraße",
                        "streetNumber": "25",
                        "zip": "10559",
                        "city": "Berlin"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500545882,
                      "contentId": "102c81ae-30d1-46c4-89c5-957eb29e25cc",
                      "title": "BESSER GLEICH ZU PORTA",
                      "validFrom": "2025-01-01T23:00:00.000+0000",
                      "validUntil": "2025-03-29T22:00:00.000+0000",
                      "publishedFrom": "2025-01-01T23:00:00.000+0000",
                      "publishedUntil": "2025-03-29T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/102c81ae-30d1-46c4-89c5-957eb29e25cc/preview.jpg",
                      "pageCount": 24,
                      "publisher": {
                        "id": "DE-34906483",
                        "name": "porta Möbel",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 16.930917857443227,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-679197138",
                        "latitude": 52.5011,
                        "longitude": 13.6225,
                        "street": "Alt-Mahlsdorf",
                        "streetNumber": "85",
                        "zip": "12623",
                        "city": "Berlin-Mahlsdorf"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500550959,
                      "contentId": "eb76c95c-450f-4fbb-92dd-f0e591aa2a8d",
                      "title": "Alles für Ihren Liebling.",
                      "validFrom": "2025-01-01T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2025-01-01T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/eb76c95c-450f-4fbb-92dd-f0e591aa2a8d/preview.jpg",
                      "pageCount": 5,
                      "publisher": {
                        "id": "DE-90881676",
                        "name": "Pflanzen Kölle",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 17.745019381106,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-90883505",
                        "latitude": 52.3883,
                        "longitude": 13.2659,
                        "street": "Ruhlsdorfer Straße / Asternstraße",
                        "streetNumber": "2",
                        "zip": "14513",
                        "city": "Teltow"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500543079,
                      "contentId": "9c057a15-ec4d-47c0-8c0b-a3e94ba4877a",
                      "title": "Mach Dein Frühstück Pflantastisch Lecker!",
                      "validFrom": "2024-12-31T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/9c057a15-ec4d-47c0-8c0b-a3e94ba4877a/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-10000002046",
                        "name": "alpro",
                        "type": "MANUFACTURER"
                      },
                      "contentBadges": [],
                      "distance": 0.22219767517052372,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-650918801",
                        "latitude": 52.5346,
                        "longitude": 13.3781,
                        "street": "Chausseestraße",
                        "streetNumber": "43",
                        "zip": "10115",
                        "city": "Berlin Mitte"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": [
                        "https://ad.doubleclick.net/ddm/trackimp/N583801.285632KAUFDA.DE/B32917250.411162959;dc_trk_aid=603302221;dc_trk_cid=227560007;ord=${'$'}{timestamp};dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=;gdpr=${'$'}{GDPR};gdpr_consent=${'$'}{GDPR_CONSENT_755};ltd=;dc_tdv=1?"
                      ]
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500545881,
                      "contentId": "22a60b15-2904-4340-8194-7e0f9d7d336a",
                      "title": "Stressless - Zeit für mich",
                      "validFrom": "2024-11-30T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2024-12-31T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/22a60b15-2904-4340-8194-7e0f9d7d336a/preview.jpg",
                      "pageCount": 8,
                      "publisher": {
                        "id": "DE-34906483",
                        "name": "porta Möbel",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 16.930917857443227,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-679197138",
                        "latitude": 52.5011,
                        "longitude": 13.6225,
                        "street": "Alt-Mahlsdorf",
                        "streetNumber": "85",
                        "zip": "12623",
                        "city": "Berlin-Mahlsdorf"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500529789,
                      "contentId": "449d4677-bd8d-461f-a5e0-149c5431a517",
                      "title": "Frühbucher",
                      "validFrom": "2024-12-19T23:00:00.000+0000",
                      "validUntil": "2025-02-28T22:00:00.000+0000",
                      "publishedFrom": "2024-12-19T23:00:00.000+0000",
                      "publishedUntil": "2025-02-28T22:00:00.000+0000",
                      "type": "DYNAMIC",
                      "brochureImage": "https://content-media.bonial.biz/449d4677-bd8d-461f-a5e0-149c5431a517/preview.jpg",
                      "pageCount": 0,
                      "publisher": {
                        "id": "DE-1624448755",
                        "name": "Center Parcs",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 234.95201016259304,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-1624449549",
                        "latitude": 53.0678,
                        "longitude": 9.99643,
                        "street": "Töpinger Straße",
                        "streetNumber": "69",
                        "zip": "29646",
                        "city": "Bispingen"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  },
                  {
                    "placement": "ad_placement__shelf_sort_managed",
                    "adFormat": "ad_format__brochure_card_cover",
                    "contentFormatSource": "destinationsApi",
                    "contentType": "brochure",
                    "content": {
                      "id": 2500522016,
                      "contentId": "9467ee41-ca31-47c9-98f1-1952ab266c53",
                      "title": "Winter Angebote",
                      "validFrom": "2024-12-11T23:00:00.000+0000",
                      "validUntil": "2025-01-31T22:00:00.000+0000",
                      "publishedFrom": "2024-12-11T23:00:00.000+0000",
                      "publishedUntil": "2025-01-31T22:00:00.000+0000",
                      "type": "BROCHURE",
                      "brochureImage": "https://content-media.bonial.biz/9467ee41-ca31-47c9-98f1-1952ab266c53/preview.jpg",
                      "pageCount": 30,
                      "publisher": {
                        "id": "DE-1509291237",
                        "name": "Kamera Express",
                        "type": "RETAILER"
                      },
                      "contentBadges": [],
                      "distance": 195.9524941060379,
                      "hideValidityDate": false,
                      "closestStore": {
                        "id": "DE-10000053785",
                        "latitude": 52.262657,
                        "longitude": 10.523515,
                        "street": "Münzstraße",
                        "streetNumber": "14",
                        "zip": "38100",
                        "city": "Braunschweig"
                      }
                    },
                    "externalTracking": {
                      "impression": [],
                      "click": []
                    }
                  }
                ]
              },
              "_links": {
                "self": {
                  "href": "https://destinationsapi.kaufda.de/v4/shelf/withStorySnippets?includeExtendedData=false&page=0&size=200"
                }
              },
              "page": {
                "size": 200,
                "totalElements": 96,
                "totalPages": 1,
                "number": 0
              }
            }
        """.trimIndent()
    }
}
