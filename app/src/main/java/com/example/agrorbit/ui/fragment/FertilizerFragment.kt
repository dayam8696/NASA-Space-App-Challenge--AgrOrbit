package com.example.agrorbit.ui.fragment

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.agrorbit.R
import com.example.agrorbit.databinding.FertilizerFragmentBinding
import com.example.agrorbit.ui.activites.CropData
import java.io.BufferedReader
import java.io.InputStreamReader

class FertilizerFragment : BaseFragments() {
    private val binding by lazy {FertilizerFragmentBinding.inflate(layoutInflater)
    }
    private lateinit var crop: String
    companion object{
        lateinit var result:String

    }
    private val fertilizerDic = mapOf(
        "NHigh" to "The N value of soil is high and might give rise to weeds.\n\n"+
                "        Please consider the following suggestions:\n\n" +
                "\n" +
                "1.  Manure  – adding manure is one of the simplest ways to amend your soil with nitrogen. Be careful as there are various types of manures with varying degrees of nitrogen.\n" +
                "\n" +
                "2. Coffee grinds  – use your morning addiction to feed your gardening habit! Coffee grinds are considered a green compost material which is rich in nitrogen. Once the grounds break down, your soil will be fed with delicious, delicious nitrogen. An added benefit to including coffee grounds to your soil is while it will compost, it will also help provide increased drainage to your soil.\n" +
                "\n" +
                "3.Plant nitrogen fixing plants – planting vegetables that are in Fabaceae family like peas, beans and soybeans have the ability to increase nitrogen in your soil\n" +
                "\n" +
                "4. Plant ‘green manure’ crops like cabbage, corn and brocolli\n" +
                "\n" +
                "5.Use mulch (wet grass) while growing crops-Mulch can also include sawdust and scrap soft woods,\n",
        "Nlow" to   "The N value of your soil is low.\n\n" +
                "        Please consider the following suggestions:\n\n" +
                "1.Add sawdust or fine woodchips to your soil –the carbon in the sawdust/woodchips love nitrogen and will help absorb and soak up and excess nitrogen.\n" +
                "\n" +
                "2.Plant heavy nitrogen feeding plants – tomatoes, corn, broccoli, cabbage and spinach are examples of plants that thrive off nitrogen and will suck the nitrogen dry.\n" +
                "\n" +
                "3.Water – soaking your soil with water will help leach the nitrogen deeper into your soil, effectively leaving less for your plants to use.\n" +
                "\n" +
                "4.Sugar– In limited studies, it was shown that adding sugar to your soil can help potentially reduce the amount of nitrogen is your soil. Sugar is partially composed of carbon, an element which attracts and soaks up the nitrogen in the soil. This is similar concept to adding sawdust/woodchips which are high in carbon content.\n" +
                "\n" +
                "5. Add composted manure to the soil.\n" +
                "\n" +
                "6. Plant Nitrogen fixing plants like peas or beans.\n" +
                "\n" +
                "7. Use NPK fertilizers with high N value.\n" +
                "\n" +
                "8. Do nothing – It may seem counter-intuitive, but if you already have plants that are producing lots of foliage, it may be best to let them continue to absorb all the nitrogen to amend the soil for your next crops.",
        "PHigh" to  "The P value of your soil is high.\n\n" +
                "       Please consider the following suggestions:\n\n" +
                "\n" +
                "1.Avoid adding manure– manure contains many key nutrients for your soil but typically including high levels of phosphorous. Limiting the addition of manure will help reduce phosphorus being added.\n" +
                "\n" +
                "2.Use only phosphorus-free fertilizer– if you can limit the amount of phosphorous added to your soil, you can let the plants use the existing phosphorus while still providing other key nutrients such as Nitrogen and Potassium. Find a fertilizer with numbers such as 10-0-10, where the zero represents no phosphorous.\n" +
                "\n" +
                "3.Water your soil– soaking your soil liberally will aid in driving phosphorous out of the soil. This is recommended as a last ditch effort.\n" +
                "\n" +
                "4.Plant nitrogen fixing vegetables to increase nitrogen without increasing phosphorous (like beans and peas).\n" +
                "\n" +
                "5.Use crop rotations to decrease high phosphorous levels",
        "Plow" to   "The P value of your soil is low.\n\n" +
                "        Please consider the following suggestions:\n\n" +
                "\n" +
                "1.Bone meal– a fast acting source that is made from ground animal bones which is rich in phosphorous.\n" +
                "\n" +
                "2. Rock phosphate– a slower acting source where the soil needs to convert the rock phosphate into phosphorous that the plants can use.\n" +
                "\n" +
                "3. Phosphorus Fertilizers – applying a fertilizer with a high phosphorous content in the NPK ratio (example: 10-20-10, 20 being phosphorous percentage).\n" +
                "\n" +
                "4. Organic compost– adding quality organic compost to your soil will help increase phosphorous content.\n" +
                "\n" +
                "5. Manure – as with compost, manure can be an excellent source of phosphorous for your plants.\n" +
                "\n" +
                "6. Clay soil– introducing clay particles into your soil can help retain & fix phosphorus deficiencies.\n" +
                "\n" +
                "7. Ensure proper soil pH– having a pH in the 6.0 to 7.0 range has been scientifically proven to have the optimal phosphorus uptake in plants.\n" +
                "\n" +
                "8. If soil pH is low, add lime or potassium carbonate to the soil as fertilizers. Pure calcium carbonate is very effective in increasing the pH value of the soil.\n" +
                "\n" +
                "9. If pH is high, addition of appreciable amount of organic matter will help acidify the soil. Application of acidifying fertilizers, such as ammonium sulfate, can help lower soil pH",
        "KHigh" to   "The K value of your soil is high</b>.\n\n" +
                "         Please consider the following suggestions:\n\n" +
                "\n" +
                "1.Loosen the soildeeply with a shovel, and water thoroughly to dissolve water-soluble potassium. Allow the soil to fully dry, and repeat digging and watering the soil two or three more times.\n" +
                "\n" +
                "2. Sift through the soil and remove as many rocks as possible, using a soil sifter. Minerals occurring in rocks such as mica and feldspar slowly release potassium into the soil slowly through weathering.\n" +
                "\n" +
                "3. Stop applying potassium-rich commercial fertilizer. Apply only commercial fertilizer that has a '0' in the final number field. Commercial fertilizers use a three number system for measuring levels of nitrogen, phosphorous and potassium. The last number stands for potassium. Another option is to stop using commercial fertilizers all together and to begin using only organic matter to enrich the soil.\n" +
                "\n" +
                "4. Mix crushed eggshells, crushed seashells, wood ash or soft rock phosphate to the soil to add calcium. Mix in up to 10 percent of organic compost to help amend and balance the soil.\n" +
                "\n" +
                "5. Use NPK fertilizers with low K levels and organic fertilizers since they have low NPK values.\n" +
                "\n" +
                "6. Grow a cover crop of legumes that will fix nitrogen in the soil. This practice will meet the soil’s needs for nitrogen without increasing phosphorus or potassium.\n" +
                "",
        "Klow" to    "The K value of your soil is low.\n\n" +
                "         Please consider the following suggestions:\n\n" +
                "\n" +
                "1. Mix in muricate of potash or sulphate of potash\n" +
                "2. Try kelp meal or seaweed\n" +
                "3. Try Sul-Po-Mag\n" +
                "4. Bury banana peels an inch below the soils surface\n" +
                "5. Use Potash fertilizers since they contain high values potassium\n" +
                ""
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationVisibility(false)
        prepareCropSpiner()
        binding.predict.setOnClickListener {
            recommendCrop()
        }

    }

    private fun prepareCropSpiner() {
        val croplist = arrayListOf<String>(
            "apple",
            "banana",
            "blackgram",
            "chickpea",
            "coconut",
            "coffee",
            "cotton",
            "grapes",
            "jute",
            "kidneybeans",
            "lentil",
            "maize",
            "mango",
            "mothbeans",
            "mungbean",
            "muskmelon",
            "orange",
            "papaya",
            "pigeonpeas",
            "pomegranate",
            "rice",
            "watermelon"
        )
        val adapter =
            ArrayAdapter(
                requireContext(),
                androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                croplist
            )

        binding.spinnerIndianState.adapter = adapter

        binding.spinnerIndianState.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    crop = croplist[position]

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
    }

    private fun abs(value: Int): Int {
        return if (value < 0) -value else value
    }

    private fun readCsvFromAssets(context: Context, fileName: String): List<String> {
        val assetManager: AssetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val lines = mutableListOf<String>()

        reader.useLines { lines.addAll(it.toList()) }

        return lines
    }

    private fun recommendCrop() {
        if (binding.btnnitrogen.text.toString().trim().isEmpty() ||
            binding.btnphosp.text.toString().trim().isEmpty() ||
            binding.btnpottasium.text.toString().trim().isEmpty()
        ){
            showToast("Please Enter All the fields")
            return
        }
        val fileName = "fertilizer.csv"
        val cropName = crop
        val N = binding.btnnitrogen.text.toString().trim().toInt()
        val P = binding.btnphosp.text.toString().trim().toInt()
        val K =binding.btnpottasium.text.toString().trim().toInt()

        val csvLines = readCsvFromAssets(requireContext(), fileName).drop(1)
            .map { it.split(",") }
            .map { CropData(it[1], it[2].toInt(), it[3].toInt(), it[4].toInt()) }

        val cropData = csvLines.find { it.crop == cropName }

        if (cropData != null) {
            val nr = cropData.N
            val pr = cropData.P
            val kr = cropData.K

            val n = nr - N
            val p = pr - P
            val k = kr - K

            val temp = mapOf(abs(n) to "N", abs(p) to "P", abs(k) to "K")
            val maxValue = temp.maxByOrNull { it.key }?.value

            val key = when (maxValue) {
                "N" -> if (n < 0) "NHigh" else "Nlow"
                "P" -> if (p < 0) "PHigh" else "Plow"
                "K" -> if (k < 0) "KHigh" else "Klow"
                else -> ""
            }

            val response = fertilizerDic[key]
            result = response?:"null"
           findNavController().navigate(R.id.action_fertilizerFragment_to_fertilizerResult)
            showToast(result)
        } else {
            showToast("Crop not found")
        }

    }


}