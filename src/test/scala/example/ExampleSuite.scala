package example

import org.automerge.Document
import scala.util.Using
import org.automerge.ObjectId
import org.automerge.ObjectType

class ExampleSuite extends munit.FunSuite:

  test("addition") {
    assert(1 + 1 == 2)
  }

  test("automerge works!") {
    val doc = Document()
    val text = Using(doc.startTransaction()) { tx =>
      val text = tx.set(ObjectId.ROOT, "text", ObjectType.TEXT)
      tx.spliceText(text, 0, 0, "Hello world");
      tx.commit()
      text
    }.get

    val docBytes = doc.save()

    val doc2 = Document.load(docBytes)
    println(doc2.text(text).get())

    Using(doc2.startTransaction()) { tx =>
      tx.spliceText(text, 5, 0, "beautiful")
      tx.commit()
    }

    Using(doc.startTransaction()) { tx =>
      tx.spliceText(text, 5, 0, " there")
      tx.commit()
    }

    doc.merge(doc2)

    println(doc.text(text).get())
  }
end ExampleSuite
