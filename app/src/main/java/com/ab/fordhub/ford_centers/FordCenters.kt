package com.ab.fordhub.ford_centers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ab.fordhub.R
import com.ab.fordhub.model.FordCenters

@Preview
@Composable
fun fordCenters(){
    FordCenters(FordCentersViewModel())
}


@Composable
fun FordCenters(viewModel: FordCentersViewModel = hiltViewModel()){

    val fordCenters by viewModel.fordCenters.observeAsState(emptyList())

    Surface(modifier = Modifier.fillMaxSize()) {

        LaunchedEffect(Unit){
            viewModel.centerDetails()
        }

        Column {

            Text(text = "Centers",  modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp),
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold)

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(){
                items(fordCenters){ center ->
                    FordCenterCard(center)
                }
            }

        }

    }
}


@Composable
fun FordCenterCard(fordCenters : FordCenters){

    Card(modifier = Modifier.fillMaxWidth().padding(10.dp), elevation = 15.dp, shape = RoundedCornerShape(10.dp)) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 20.dp)) {
            val (image,locationTitle,location,workingTimeTitle, workingTime,serviceTitle, service) = createRefs()

            AsyncImage(model = fordCenters.url,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_load_placeholder),
                error = painterResource(id = R.drawable.ic_load_error),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .constrainAs(image){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .height(60.dp)
                    .width(60.dp).padding(start = 15.dp))

            Text(text = "Location : ", modifier =
            Modifier.constrainAs(locationTitle){
                top.linkTo(parent.top)
                start.linkTo(image.end)
            }.padding(start = 5.dp))


            Text(text = "${fordCenters.location}", modifier =
                Modifier.constrainAs(location){
                    top.linkTo(parent.top)
                    start.linkTo(locationTitle.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                })

            Text(text = "Working Time : ", modifier =
            Modifier.constrainAs(workingTimeTitle){
                top.linkTo(location.bottom)
                start.linkTo(image.end)
            }.padding(top = 5.dp, start = 5.dp))


            Text(text = "${fordCenters.workingTime}", modifier =
            Modifier.constrainAs(workingTime){
                top.linkTo(location.bottom)
                start.linkTo(workingTimeTitle.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }.padding(top = 5.dp))

            Text(text = "Service :", modifier =
            Modifier.constrainAs(serviceTitle){
                top.linkTo(workingTime.bottom)
                start.linkTo(image.end)
            }.padding(top = 5.dp, start = 5.dp))

            Text(text = "${fordCenters.service}", modifier =
            Modifier.constrainAs(service){
                top.linkTo(workingTime.bottom)
                start.linkTo(serviceTitle.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }.padding(top = 5.dp))


        }
    }

}