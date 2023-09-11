package com.ab.fordhub.paging

//class VehiclePagingSource @Inject constructor(
//    private val api: FordApiService
//) : PagingSource<Int, List<VehicleDetail>>() {
//
//    override fun getRefreshKey(state: PagingState<Int, List<VehicleDetail>>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<VehicleDetail>> {
//        return try {
//            val nextPage = params.key ?: 1
//            val popularMovies = api.getRecentVehicles(nextPage)
//            LoadResult.Page(
//                data = listOf( popularMovies),
//                prevKey = if (nextPage == 1) null else nextPage - 1,
//                nextKey = if (popularMovies.isEmpty()) null else nextPage + 1
//            )
//        } catch (e: java.lang.Exception) {
//            LoadResult.Error(throwable = RequestErrorHandler.getRequestError(e))
//        }
//    }
//}