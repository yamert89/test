package roslesinforg.porokhin.geobaseeditor

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.Labeled
import javafx.scene.control.TextField
import roslesinforg.porokhin.areaselector.ComparingCondition
import roslesinforg.porokhin.areaselector.LogicCondition
import roslesinforg.porokhin.areaselector.ParameterFactory
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.ShortCodes
import roslesinforg.porokhin.areatypes.parameters.Attribute
import roslesinforg.porokhin.geobaseeditor.view.MainView
import tornadofx.*


abstract class ParamView(val param: ComboBox<Attribute>, val columnNumber: ComboBox<String>,
                         var comboBoxValue: ComboBox<String>, val textFieldValue: TextField,
                         val previousParamView: ParamView? = null, val isActive: SimpleBooleanProperty,
){

    protected fun fillValueItems(attribute: Attribute): List<String> {
        val codes = ShortCodes //TODO type-hardcoded link
        var sum = 0
        return when(attribute) {
            Attribute.OZU -> codes.typesOfProtection.values.toList()
            Attribute.CATEGORY_PROTECTION -> codes.categoryProtection.values.toList()
            Attribute.SPECIES -> codes.species
            Attribute.BON -> codes.bons
            Attribute.WEIGHT -> listOf("0,3", "0,4", "0,5", "0,6", "0,7", "0,8", "0,9", "1,0", "1,1", "1,2", "1,3", "1,4", "1,5")
            Attribute.SUM_OF_TIMBER ->  generateSequence {
                sum += 10
                if (sum < 360) sum else null
            }.map { i -> i.toString() }.asIterable().toMutableList().apply { this.add(0, "5") }
            Attribute.CATEGORY -> codes.categoryArea.values.toList()
            Attribute.INFO -> (11..999).map { i -> i.toString() }.toList()
            Attribute.KV, Attribute.VID -> (1..400).map { it.toString() }.toList()
            Attribute.LESB -> TODO()
            Attribute.EMPTY -> throw IllegalArgumentException("Empty attribute")
            else -> throw IllegalArgumentException("Unsupported attribute")
        }
    }

    protected fun fillColumnNumberItems(attribute: Attribute) = when(attribute)

    infix fun Int.toStringList(end: Int) = (this..end).map { it.toString() }.toList()
}