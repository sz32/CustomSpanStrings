# CustomSpanStrings

CustomSpanString is a class which you can use for applying multiple spans in single textview the spans you can use for Bold, Underline, Color and Clickable


#Usage
```
CustomSpanStrings()
.setCompleteString(completeString)
.setStrings(termsOfUseLink, privacyPolicy)
.setClickableSpan(object : ClickAbleCustomSpanListener {
  override fun onClickSpan() {
    Toast.makeText(context, "Toast New", Toast.LENGTH_SHORT).show()
  }
})
.setBoldSpan()
.setUnderlineSpan()
.setColorSpan(Color.BLACK, Color.BLACK)
.setTextView(termsAndCondn)
.build()
```

