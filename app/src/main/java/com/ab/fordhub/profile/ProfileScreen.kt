package com.ab.fordhub.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ab.fordhub.R
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius

@Preview
@Composable
fun ProfileScreen( ){

    Surface(modifier = Modifier.fillMaxSize()){

        Column {
            Surface(
                color = Color.LightGray,
                elevation = 40.dp,
                shape = CircleShape,
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Name : Asmiya Begum N", modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Email : asmiyabegum.nmr@gmail.com", modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Phone number : +91 9003606100", modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Logged-In time : 10.20 AM", modifier = Modifier.padding(10.dp))





        }
    }
}