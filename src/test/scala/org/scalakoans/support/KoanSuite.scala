package org.scalakoans.support

import org.scalatest._
import org.scalatest.events.{Event, TestFailed, TestSucceeded}

trait KoanSuite extends FunSuite with CancelAfterFailure with Matchers {

  override def runTests(testName: Option[String], args: Args): Status = {

    if (testName == null)
      throw new NullPointerException("testName was null")
    if (args.reporter == null)
      throw new NullPointerException("reporter was null")
    if (args.stopper == null)
      throw new NullPointerException("stopper was null")
    if (args.filter == null)
      throw new NullPointerException("filter was null")
    if (args.configMap == null)
      throw new NullPointerException("configMap was null")
    if (args.distributor == null)
      throw new NullPointerException("distributor was null")
    if (args.tracker == null)
      throw new NullPointerException("tracker was null")

    class KoanReporter(wrappedReporter: Reporter) extends Reporter {
      var succeeded = false

      override def apply(event: Event) = {
        event match {
          case e: TestSucceeded => succeeded = true
          case e: TestFailed =>
            note("*****************************************")
            note("")
            note(s"Student should meditate on: ${e.testText}")
            note(s"In ${e.suiteName}")
            note("")
            note("*****************************************")
            args.stopper.requestStop()
          case _ =>
        }
        wrappedReporter(event)
      }
    }

    // If a testName is passed to run, just run that, else run the tests returned by testNames.
    testName match {
      case Some(tn) => runTest(tn, args)
      case None =>
        val tests = testNames.iterator
        var failed = false
        for (test <- tests) {
          if (!failed) {
            val koanReporter = new KoanReporter(args.reporter)

            runTest(test, args.copy(reporter = koanReporter))
            failed = !koanReporter.succeeded
          }
        }
        if (failed) FailedStatus else SucceededStatus
    }
  }

  def koan(name: String)(fun: => Unit) = test(name.stripMargin)(fun)
}

