package com.elion.assistant.domain.model

data class Category(
    val id: Long = 0,
    val name: String,
    val colorHex: String,
    val iconName: String,
    val isDefault: Boolean = false,
    val sortOrder: Int = 0,
) {
    companion object {
        val defaults = listOf(
            Category(1, "İş",        "#4A90D9", "work",           isDefault = true, sortOrder = 0),
            Category(2, "Kişisel",   "#7B68EE", "person",         isDefault = true, sortOrder = 1),
            Category(3, "Sağlık",    "#2ECC71", "favorite",       isDefault = true, sortOrder = 2),
            Category(4, "Alışveriş","#F39C12",  "shopping_cart",  isDefault = true, sortOrder = 3),
            Category(5, "Finans",    "#E74C3C",  "account_balance",isDefault = true, sortOrder = 4),
            Category(6, "Diğer",     "#9E9E9E",  "label",          isDefault = true, sortOrder = 5),
        )
    }
}
