/**
 * 
 */
package au.edu.anu.dspaceimporter.sword.task;

import org.swordapp.client.SWORDClient;
import org.swordapp.client.SwordResponse;

import au.edu.anu.dspaceimporter.sword.SwordServerInfo;

/**
 * @author Rahul Khanna
 *
 */
public class ChangeInProgressTask extends AbstractSwordTask<SwordResponse> {

	private String editUrl;

	public ChangeInProgressTask(SWORDClient swordClient, SwordServerInfo serverInfo, String editUrl) {
		super(swordClient, serverInfo);
		this.editUrl = editUrl;
	}

	@Override
	public SwordResponse call() throws Exception {
		SwordResponse sr = this.client.complete(this.editUrl, createAuth(false));
		return sr;
	}

}
