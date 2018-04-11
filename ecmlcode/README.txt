Main class: ConceptDriftEnsemble.java
This class implements CutPointDetector, which is under the folder cutpointdetection.

Use:
ConceptDriftEnsemble(CutPointDetector[] ensemble, double k, double windowSize)
where k = the number of detectors to reach majority (if 5 detectors then k = 3)
windowSize = the size for sliding window (default is 500)

The constructor takes in an array of detectors and aggregates their signals.
The method setInput(double input) should be called for each instance. The ensemble will pass the input to all other detectors within.

--

Folder cutpointdetection:
Consists of detectors taken from MOA package directly which implements interface CutPointDetector (a derivative of MOA's Abstract Change Detector class)
Other/future MOA change detectors can be used in this ensemble.

--

FalsePositiveWithNoise_Comparison.xlsx:
This is supplementary results when noise was added to the Bernoulli false positive dataset. 
This tests the robustness of CDE and the detectors to noise.
The result shows CDE is robust to noise compared to other detectors.