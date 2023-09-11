package com.ab.fordhub.user_register

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.ab.fordhub.R
import com.ab.fordhub.common.GradientButton
import com.ab.fordhub.route.Root
import com.ab.fordhub.route.Route
import com.ab.fordhub.ui.theme.FordHubTheme
import com.ab.fordhub.ui.theme.darkIndigo
import com.ab.fordhub.ui.theme.md_theme_light_primary
import com.ab.fordhub.ui.theme.midnightBlue
import com.ab.fordhub.ui.theme.royalBlue
import com.ab.fordnavigation.NavInfo

@Preview
@Composable
fun UserRegisterScreen(viewModel: UserRegisterViewModel = hiltViewModel()){

//    val startForResult = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            if (result.resultCode == 123) {
//                if (result.data != null) {
//                        viewModel.signInService.getSignInResultFromIntent(result.data!!)
//                }
//            }
//        }
//
//    val signInIntent = viewModel.signInClientIntent.observeAsState()
//
//    LaunchedEffect(key1 = signInIntent) {
//        startForResult.launch(signInIntent.value)
//    }
//
//
//    val signInResult = viewModel.signInResult.observeAsState()
//
//    LaunchedEffect(key1 = signInResult) {
//        if(signInResult.value?.errorMessage == null){
//            Log.d("signInStatus","logged")
//        }
//    }

    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column {
            var userCredentialState by remember { mutableStateOf("") }
            var passwordState by remember { mutableStateOf("") }
            val inputFieldColor = darkIndigo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_login),
                    contentDescription = "User Login Image",
                    modifier = Modifier.height(250.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = userCredentialState,
                onValueChange = { userCredentialState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                label = {
//                    Text(text = " User Name or Email")
                        },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email,
                        contentDescription ="Email icon",
                        // tint = myColor5
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = inputFieldColor,
                    focusedLabelColor = inputFieldColor,
                    cursorColor = inputFieldColor,
                    textColor = inputFieldColor,
                    leadingIconColor = inputFieldColor,
                    backgroundColor = inputFieldColor.copy(
                        TextFieldDefaults.BackgroundOpacity),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent),
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                value = passwordState,
                onValueChange = { passwordState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                label = {
//                    Text(text = " Password")
                        },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountBox,
                        contentDescription ="Email icon",
                        // tint = myColor5
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = inputFieldColor,
                    focusedLabelColor = inputFieldColor,
                    cursorColor = inputFieldColor,
                    textColor = inputFieldColor,
                    leadingIconColor = inputFieldColor,
                    backgroundColor = inputFieldColor.copy(
                        TextFieldDefaults.BackgroundOpacity),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))


                GradientButton(text = "Login", textColor = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally),
                    gradient = Brush.linearGradient(listOf( darkIndigo, royalBlue, midnightBlue))) {
                    viewModel.navManager.navigate(
                        NavInfo(id = Root.HOME,
                            navOption = NavOptions.Builder().setPopUpTo(Route.Welcome.SPLASH, inclusive = true).build())
                    )
                }


            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "Or", color = darkIndigo, modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(5.dp))

            GradientButton(text = "Sign In With Google", textColor = midnightBlue, modifier = Modifier.align(Alignment.CenterHorizontally), gradient = Brush.horizontalGradient(
                listOf(Color.Gray, Color.White, Color.Gray)
            )) {

            }


        }
    }

}