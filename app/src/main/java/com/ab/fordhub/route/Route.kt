package com.ab.fordhub.route

/** Class contains all route information*/
object Route {
    object Control {
        const val Back = "back"
    }
    object Welcome {
        const val SPLASH = "splash"
        const val LOGIN = "login"
    }

    object Home {
        object TestDrive {
            const val VEHICLE_LIST = "vehicle_list"
            const val VEHICLE_DETAIL ="vehicle_detail"
            const val TESTDRIVE_BOOKING ="eventemptyscreen"
        }

        object Services {
            const val SERVICE_LIST = "service_list"
            const val SERVICE_DETAIL = "service_detail"
        }

            const val CENTERS = "center"


        const val PROFILE = "profile"


    }


}

/** To hold the nav graph roots */
object Root {
    const val APPROOT = "approot"
    const val WELCOME = "welcomeroot"
    const val HOME = "homeroot"


    object Home {
        const val TESTDRIVE = "testdrive"

        object  TestDrive {
            const val VEHICLE_DETAIL = "vehicledetail"
        }
        const val SERVICES = "services"
    }

}