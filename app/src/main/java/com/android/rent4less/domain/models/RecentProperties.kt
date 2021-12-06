import com.android.rent4less.domain.models.Results
import com.google.gson.annotations.SerializedName

data class RecentProperties (

	@SerializedName("count") val count : Int?,
	@SerializedName("next") val next : String?,
	@SerializedName("previous") val previous : String?,
	@SerializedName("results") val results : List<Results?>?
)