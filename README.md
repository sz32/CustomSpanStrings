# CustomSpanStrings

CustomSpanString is a class which you can use for applying multiple spans in single textview the spans you can use for Bold, Underline, Color and Clickable


## Usage
```kotlin
val greeting = "Hello"
val name = "SpannableString"

// Important: Set final text in textView before using CustomSpanStrings
val title = "$greeting, $name"
textView.text = title

CustomSpanStrings.withTextView(textView)
    .addClickListener(name) {
        // Do something when $name is clicked
    }
    .applyBold(name) // To apply bold to $name
    .applyUnderline(name) // To apply underline to $name
    .applyColor(Color.GRAY, name) // To change color of $name
    .applyColor(Color.LTGRAY, greeting) // To change color of $greeting
    .commit() // Finally commit all changes
```

### Initialize
To initialize SpannableStringBuilder using the text from textView
```kotlin
CustomSpanStrings.withTextView(textView)
```

### Clickable
To add click listener to $name text.
```kotlin
.addClickListener(name) {
    // Do something when $name is clicked
}
```

### Bold
To apply bold to $name text. You can pass multiple texts to apply bold effect to the texts. 
```kotlin
.applyBold(name)
```

### Underline
To apply underline to $name text. You can pass multiple texts to apply underline effect to the texts.
```kotlin
.applyUnderline(name)
```

### Color
To apply grey color to $name text. You can pass multiple texts to apply same color to the texts.
```kotlin
.applyColor(Color.GRAY, name)
```

### Commit
For applying all spans and set to your textview
```kotlin
.commit()
```

![](asset/preview.jpeg)
