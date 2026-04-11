package com.bonial.brochure.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bonial.brochure.presentation.detail.BrochureDetailScreen
import com.bonial.brochure.presentation.detail.BrochureDetailViewModel
import com.bonial.brochure.presentation.home.BrochuresScreen
import com.bonial.brochure.presentation.home.BrochuresViewModel

@Composable
fun BrochureNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BrochureListRoute,
    ) {
        composable<BrochureListRoute> {
            val viewModel: BrochuresViewModel = hiltViewModel()
            BrochuresScreen(
                viewModel = viewModel,
                onBrochureClick = { brochure ->
                    navController.navigate(
                        BrochureDetailRoute(
                            title = brochure.title,
                            coverUrl = brochure.coverUrl,
                            publisherName = brochure.publisherName,
                            distance = brochure.distance,
                        ),
                    )
                },
            )
        }

        composable<BrochureDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<BrochureDetailRoute>()
            val viewModel: BrochureDetailViewModel = hiltViewModel()
            BrochureDetailScreen(
                title = route.title,
                coverUrl = route.coverUrl,
                publisherName = route.publisherName,
                distance = route.distance,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
